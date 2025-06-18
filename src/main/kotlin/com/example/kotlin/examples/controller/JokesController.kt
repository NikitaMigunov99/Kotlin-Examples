package com.example.kotlin.examples.controller

import com.example.kotlin.examples.model.domain.JokeModel
import com.example.kotlin.examples.model.dto.JokeRequestDTO
import com.example.kotlin.examples.service.JokesService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class JokesController(private val jokesService: JokesService) {

    @GetMapping("/joke")
    fun getJoke(): ResponseEntity<JokeModel?> {
        println("Received request on thread " + Thread.currentThread().name + " " + Thread.currentThread().id)
        val joke: JokeModel = jokesService.getJoke()
        return ResponseEntity.ok(joke)
    }

    @GetMapping("/joke/{id}")
    fun getJokeById(@PathVariable id: Long): ResponseEntity<JokeModel> {
        println("Received request on thread " + Thread.currentThread().name + " " + Thread.currentThread().id)
        val joke: JokeModel = jokesService.getJokeById(id)
        return ResponseEntity.ok(joke)
    }

    @GetMapping("/jokes")
    fun getJokes(): ResponseEntity<List<JokeModel>> {
        println("Received request on thread " + Thread.currentThread().name + " " + Thread.currentThread().id)
        val list: List<JokeModel> = jokesService.getJokes()
        return ResponseEntity.ok(list)
    }

    @PostMapping("/save/joke")
    fun saveJoke(@RequestBody request: JokeRequestDTO): ResponseEntity<String> {
        println("Received request on thread " + Thread.currentThread().name + " " + Thread.currentThread().id)
        jokesService.saveJoke(request)
        return ResponseEntity.ok(JOKE_SAVED)
    }

    @PostMapping("/save/joke/remote/{id}")
    fun saveJokeById(@PathVariable id: String): ResponseEntity<String> {
        println("Received request on thread " + Thread.currentThread().name + " " + Thread.currentThread().id)
        jokesService.saveJokeByIdFromRemote(id)
        return ResponseEntity.ok(JOKE_SAVED)
    }

    @GetMapping("/jokes/count")
    fun getJokesCount(): ResponseEntity<String> {
        val count: Int = jokesService.getJokesCount()
        return ResponseEntity.ok("Jokes count is $count")
    }

    @GetMapping("/jokes/type")
    fun getJokeByType(@RequestParam type: String): ResponseEntity<List<JokeModel>> {
        return ResponseEntity.ok(jokesService.getJokeByType(type))
    }

    @PutMapping("update/joke/type/{id}")
    fun updateJokesType(@PathVariable id: Long, @RequestBody request: JokeRequestDTO): ResponseEntity<String> {
        jokesService.updateJoke(id, request)
        return ResponseEntity.ok("Jokes type updated")
    }

    private companion object{
        const val JOKE_SAVED = "Joke was successfully saved"
    }
}