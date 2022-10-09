package com.adi3000.cloud.bridge.webhooks

import com.adi3000.cloud.bridge.BridgeService
import com.adi3000.cloud.slack.SlackHandler
import com.google.gson.JsonObject
import com.slack.api.model.event.MessageEvent
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import jakarta.inject.Inject
import org.slf4j.LoggerFactory

@Controller("/webhooks")
class WebhooksController(
    @Inject val bridgeService: BridgeService,
    @Inject val slackHandler: SlackHandler
) {

    companion object{
        val LOGGER = LoggerFactory.getLogger(WebhooksController::class.java)
    }

    @Post(uri="/slack", produces=[MediaType.APPLICATION_JSON])
    fun slackWebhook(@Body jsonString:String): Map<String, String> {
        val body = slackHandler.getJsonObject(jsonString)
        LOGGER.trace("Get event {}", body)
        val challenge = "${body["challenge"] ?:""}"
        if (challenge.isBlank()) {
            processSlackEvent(body)
        }
        return mapOf("challenge" to challenge)
    }

    fun processSlackEvent(body: JsonObject) {
        val eventNode = body["event"] ?: throw IllegalArgumentException("Cannot find <event> attribute on $body")
        val event = slackHandler.getEvent(eventNode.asJsonObject["type"].asString, eventNode.asJsonObject)
        when (event) {
            is MessageEvent -> bridgeService.forwardSlackFilesToNextcloud(event)
        }
        LOGGER.trace("Event found was {} : {}",event.type,  event)
    }
}
