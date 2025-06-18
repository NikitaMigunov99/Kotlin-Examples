package com.example.kotlin.examples.mapper

import com.example.kotlin.examples.model.db.JokeEntity
import com.example.kotlin.examples.model.domain.JokeModel
import org.springframework.stereotype.Component

@Component
class JokesEntityToDomainMapper {

    fun convert(dbList: List<JokeEntity>): List<JokeModel> = dbList.map(::toModel)


    fun toModel(entity: JokeEntity): JokeModel {
        return JokeModel(
            entity.id ?: 0L,
            entity.type,
            entity.setup,
            entity.punchline
        )
    }
}