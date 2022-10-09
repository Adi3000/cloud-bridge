package com.adi3000.cloud.slack.model

class SlackProperties {
    var defaultChannel = SlackChannel()
    lateinit var users: List<SlackUser>
    lateinit var token: String
}