package com.adi3000.cloud.slack.model

import io.micronaut.serde.annotation.Serdeable.Serializable

@Serializable
class SlackUser{
    lateinit var id: String
    lateinit var user: String
}
