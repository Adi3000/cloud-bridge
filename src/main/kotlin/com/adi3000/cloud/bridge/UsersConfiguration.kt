package com.adi3000.cloud.bridge

import com.adi3000.cloud.bridge.model.UsersProperties
import io.micronaut.context.annotation.Value
import io.micronaut.context.event.StartupEvent
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton

@Singleton
class UsersConfiguration (@Value("\${bridge.users.file}") filePath:String)
    : BridgeConfiguration<UsersProperties>(filePath){
    lateinit var properties: UsersProperties

    @EventListener
    fun loadProperties(@Suppress("UNUSED_PARAMETER")event: StartupEvent){
        properties = loadProperties(UsersProperties::class.java)
    }

}