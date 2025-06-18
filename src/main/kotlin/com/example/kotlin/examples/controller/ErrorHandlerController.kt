package com.example.kotlin.examples.controller

import org.springframework.dao.DataRetrievalFailureException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ErrorHandlerController {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(ex: NoSuchElementException): ResponseEntity<String> {
        return ResponseEntity("Element not found", HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(DataRetrievalFailureException::class)
    fun handleEmptyResultDataAccessException(ex: EmptyResultDataAccessException): ResponseEntity<String> {
        return ResponseEntity("No data found: ${ex.message}", HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneralException(ex: Exception): ResponseEntity<String> {
        return ResponseEntity("Internal Server Error: ${ex.message}", HttpStatus.INTERNAL_SERVER_ERROR)
    }
}