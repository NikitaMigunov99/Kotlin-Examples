package com.example.kotlin.examples.model.domain


data class JokeModel(
    val id: Long,
    val type: String,
    val setup: String,
    val punchline: String
)
