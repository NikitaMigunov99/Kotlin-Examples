package com.example.kotlin.examples.model.db

import jakarta.persistence.*

@Entity
@Table(name = "jokes")
data class JokeEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jokes_seq")
    @SequenceGenerator(name = "jokes_seq", sequenceName = "jokes_seq", allocationSize = 1)
    val id: Long? = null,

    @Column(nullable = false)
    var type: String,

    @Column(nullable = false)
    var setup: String,

    @Column(nullable = false)
    var punchline: String
) {
    constructor() : this(null, "", "", "")

    constructor(type: String, setup: String, punchline: String) : this(null, type, setup, punchline)
}
