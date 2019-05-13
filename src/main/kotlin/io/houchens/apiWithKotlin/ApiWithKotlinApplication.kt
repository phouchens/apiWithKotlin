package io.houchens.apiWithKotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class ApiWithKotlinApplication

fun main(args: Array<String>) {
    runApplication<ApiWithKotlinApplication>(*args)
}
