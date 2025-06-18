package com.example.kotlin.examples.service

import com.example.kotlin.examples.api.JokesClient
import com.example.kotlin.examples.mapper.JokesEntityToDomainMapper
import com.example.kotlin.examples.model.db.JokeEntity
import com.example.kotlin.examples.model.domain.JokeModel
import com.example.kotlin.examples.model.dto.JokeRequestDTO
import com.example.kotlin.examples.repository.JokesRepository
import org.springframework.stereotype.Service

@Service
class JokesService(
    private val repository: JokesRepository,
    private val mapper: JokesEntityToDomainMapper,
    private val jokesClient: JokesClient
) {

    fun getJokes(): List<JokeModel> {
        val entities: List<JokeEntity> = repository.findAll()
        return mapper.convert(entities)
    }

    fun getJoke(): JokeModel {
        val entity = repository.findFirstJoke()
        return mapper.toModel(entity)
    }

    fun getJokeById(id: Long): JokeModel {
        val entity: JokeEntity = repository.findById(id).orElseThrow()
        return mapper.toModel(entity)
    }

    fun saveJoke(request: JokeRequestDTO) {
        repository.save(JokeEntity(request.type, request.setup, request.punchline))
    }

    fun saveJokeByIdFromRemote(id: String) {
        val model = jokesClient.getJokeById(id)
        repository.save(JokeEntity(model.type, model.setup, model.punchline))
    }

    fun updateJoke(id: Long, request: JokeRequestDTO): JokeModel {
        val entity: JokeEntity = repository.findById(id).orElseThrow()
        entity.type = request.type
        entity.setup = request.setup
        entity.punchline = request.punchline
        repository.save(entity)
        return mapper.toModel(entity)
    }

    fun getJokesCount(): Int {
        return repository.getJokesCount()
    }

    fun getJokeByType(type: String): List<JokeModel> {
        val entities = repository.getJokeByType(type)
        return mapper.convert(entities)
    }
}