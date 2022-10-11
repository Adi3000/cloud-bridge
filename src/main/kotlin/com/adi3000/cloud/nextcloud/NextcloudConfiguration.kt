package com.adi3000.cloud.nextcloud

import com.adi3000.cloud.bridge.BridgeConfiguration
import com.adi3000.cloud.nextcloud.model.NextcloudProperties
import io.micronaut.context.annotation.Value
import io.micronaut.context.event.StartupEvent
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton

@Singleton
class NextcloudConfiguration (@Value("\${bridge.mapping.nextcloud}") filePath:String)
    : BridgeConfiguration<NextcloudProperties>(filePath){
    lateinit var properties: NextcloudProperties

    @EventListener
    fun loadProperties(@Suppress("UNUSED_PARAMETER") event: StartupEvent){
        properties = loadProperties(NextcloudProperties::class.java)
    }

}