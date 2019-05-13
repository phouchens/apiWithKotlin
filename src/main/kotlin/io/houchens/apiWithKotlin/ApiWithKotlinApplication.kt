package io.houchens.apiWithKotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiWithKotlinApplication

fun main(args: Array<String>) {
	runApplication<ApiWithKotlinApplication>(*args)
}
