package com.example.kotlin.examples

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.transaction.annotation.EnableTransactionManagement

//@SpringBootApplication(exclude = [HibernateJpaAutoConfiguration::class, JpaRepositoriesAutoConfiguration::class])
@SpringBootApplication
@EnableFeignClients(basePackages = ["com.example.kotlin.examples.api"])
//@EnableTransactionManagement
class KotlinExamplesApplication

fun main(args: Array<String>) {
	runApplication<KotlinExamplesApplication>(*args)
}
