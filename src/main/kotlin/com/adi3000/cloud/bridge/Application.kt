package com.adi3000.cloud.bridge

import io.micronaut.runtime.Micronaut.*
import io.swagger.v3.oas.annotations.*
import io.swagger.v3.oas.annotations.info.*

@OpenAPIDefinition(
    info = Info(
            title = "cloud-bridge",
            version = "0.1"
    )
)
object Api {
}

fun main(args: Array<String>) {
	run(*args)
}

