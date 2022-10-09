package com.adi3000.cloud.bridge.model

import io.micronaut.serde.annotation.Serdeable.Serializable

@Serializable
class User {
    lateinit var login: String
    lateinit var mail: String
}