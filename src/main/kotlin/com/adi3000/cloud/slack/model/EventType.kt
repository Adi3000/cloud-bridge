package com.adi3000.cloud.slack.model

import com.slack.api.model.event.BotAddedEvent
import com.slack.api.model.event.Event
import com.slack.api.model.event.FileCreatedEvent
import com.slack.api.model.event.FileDeletedEvent
import com.slack.api.model.event.FileSharedEvent
import com.slack.api.model.event.MessageBotEvent
import com.slack.api.model.event.MessageChannelTopicEvent
import com.slack.api.model.event.MessageEvent
import com.slack.api.model.event.MessageGroupTopicEvent
import com.slack.api.model.event.MessageMeEvent
import com.slack.api.model.event.MessageMetadataPostedEvent
import com.slack.api.model.event.ReactionAddedEvent
import com.slack.api.model.event.ReactionRemovedEvent

enum class EventType (val type:String, val clazz:Class<out Event>){
    FILE_SHARED(FileSharedEvent.TYPE_NAME, FileSharedEvent::class.java),
    BOT_ADDED_EVENT(BotAddedEvent.TYPE_NAME, BotAddedEvent::class.java),
    FILE_CREATED(FileCreatedEvent.TYPE_NAME, FileCreatedEvent::class.java),
    FILE_DELETED(FileDeletedEvent.TYPE_NAME, FileDeletedEvent::class.java),
    MESSAGE(MessageEvent.TYPE_NAME, MessageEvent::class.java),
    MESSAGE_METADATA(MessageMetadataPostedEvent.TYPE_NAME, MessageMetadataPostedEvent::class.java),
    REACTION_ADD(ReactionAddedEvent.TYPE_NAME, ReactionAddedEvent::class.java),
    REACTION_REMOVE(ReactionRemovedEvent.TYPE_NAME, ReactionRemovedEvent::class.java),
    MESSAGE_ME(MessageMeEvent.TYPE_NAME, MessageMeEvent::class.java),
    MESSAGE_GROUP(MessageGroupTopicEvent.TYPE_NAME, MessageGroupTopicEvent::class.java),
    MESSAGE_CHANNEL(MessageChannelTopicEvent.TYPE_NAME, MessageChannelTopicEvent::class.java),
    MESSAGE_BOT(MessageBotEvent.TYPE_NAME, MessageBotEvent::class.java);


    companion object{
        fun getByTypename(type:String) =
            values().firstOrNull { eventType -> eventType.type == type } ?: throw NotImplementedError(type)
    }
}