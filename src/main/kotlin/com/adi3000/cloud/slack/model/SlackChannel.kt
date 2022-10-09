package com.adi3000.cloud.slack.model

import io.micronaut.serde.annotation.Serdeable.Serializable

@Serializable
class SlackChannel{
    var id: String = ""
    var saveFile: Boolean = true
    var readMessage: Boolean = true
}
