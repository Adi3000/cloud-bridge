package com.adi3000.cloud.slack

import com.adi3000.cloud.bridge.BridgeConfiguration
import com.adi3000.cloud.slack.model.SlackProperties
import io.micronaut.context.annotation.Value
import io.micronaut.context.event.StartupEvent
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton

@Singleton
class SlackConfiguration (@Value("\${bridge.mapping.slack}") filePath:String)
    : BridgeConfiguration<SlackProperties>(filePath){
    lateinit var properties: SlackProperties

    @EventListener
    fun loadProperties(@Suppress("UNUSED_PARAMETER") event: StartupEvent){
        properties = loadProperties(SlackProperties::class.java)
    }

}