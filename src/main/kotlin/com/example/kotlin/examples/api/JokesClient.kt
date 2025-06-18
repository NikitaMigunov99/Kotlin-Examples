package com.example.kotlin.examples.api

import com.example.kotlin.examples.model.domain.JokeModel
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(value = "jokes", url = "https://official-joke-api.appspot.com")
interface JokesClient {

    @GetMapping(value = ["/random_joke"])
    fun getJoke(): JokeModel

    @GetMapping(value = ["/jokes/{id}"])
    fun getJokeById(@PathVariable("id") id: String): JokeModel

    @GetMapping(value = ["/random_ten"])
    fun getJokesList(): List<JokeModel?>
}