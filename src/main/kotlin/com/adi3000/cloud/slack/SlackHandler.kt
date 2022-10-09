package com.adi3000.cloud.slack

import com.adi3000.cloud.slack.model.EventType
import com.adi3000.cloud.slack.model.SlackFile
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.slack.api.Slack
import com.slack.api.model.File
import com.slack.api.model.event.MessageEvent
import com.slack.api.util.json.GsonFactory
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption


@Singleton
class SlackHandler (@Inject val config: SlackConfiguration){
    companion object{
        val GSON: Gson = GsonFactory.createSnakeCase()
        val API: Slack = Slack()
    }

    fun getEvent(type:String, eventNode:JsonObject) = GSON.fromJson(eventNode, EventType.getByTypename(type).clazz)

    fun getJsonObject(jsonString: String) = GSON.fromJson(jsonString, JsonObject::class.java)

    fun saveFiles(message:MessageEvent) =
        (message.files?: emptyList()).map { getFile(it, message.user, message.channel) }

    private fun getFile(file: File, user: String, channelId: String): SlackFile {
        val slackUser= config.properties.users.first { it.id == user }
        val channel = getChannelName(channelId)
        val savedFile= saveToTempfile(file)
        return SlackFile(filename= file.name, file= savedFile, channel= channel?:"", user = slackUser.user)
    }

    private fun saveToTempfile(file:File): Path {
        val savedFile= Files.createTempFile("slack-${file.id}-", "-${file.name}")
        API.httpClient.get(file.urlPrivateDownload, null, config.properties.token).body?.let {
            it.byteStream().use { bytes->
                Files.copy(bytes, savedFile, StandardCopyOption.REPLACE_EXISTING)
            }
        }
        return savedFile
    }

    //TODO put to cache
    private fun getChannelName(channelId: String): String? =
        API.httpClient.get("https://slack.com/api/conversations.info?channel=$channelId", null, config.properties.token)
            .body?.let{
                getJsonObject(it.string())["channel"].asJsonObject["name"].asString
            }
}