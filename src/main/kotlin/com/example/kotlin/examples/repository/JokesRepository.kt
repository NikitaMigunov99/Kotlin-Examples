package com.example.kotlin.examples.repository

import com.example.kotlin.examples.model.db.JokeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface JokesRepository : JpaRepository<JokeEntity, Long> {

    @Query(value = "SELECT * FROM jokes LIMIT 1", nativeQuery = true)
    fun findFirstJoke(): JokeEntity

    fun getJokeByType(type: String): List<JokeEntity>

    @Query("select count(j) from JokeEntity j")
    fun getJokesCount(): Int

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE joke", nativeQuery = true)
    fun truncateTable()
}