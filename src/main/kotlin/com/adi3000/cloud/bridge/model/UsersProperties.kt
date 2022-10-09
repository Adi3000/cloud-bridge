package com.adi3000.cloud.bridge.model

import io.micronaut.serde.annotation.Serdeable.Serializable

@Serializable
class UsersProperties{
    lateinit var users:List<User>
}
