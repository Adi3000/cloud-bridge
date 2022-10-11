package com.adi3000.cloud.slack.model

import java.nio.file.Path

data class SlackFile (val filename: String, val file: Path, val channel: String, val user: String)