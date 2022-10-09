package com.adi3000.cloud.bridge

import com.adi3000.cloud.nextcloud.NextcloudHandler
import com.adi3000.cloud.slack.SlackHandler
import com.slack.api.model.event.MessageEvent
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class BridgeService(@Inject val slackHandler: SlackHandler,
                    @Inject val nextcloudHandler: NextcloudHandler
) {

    fun forwardSlackFilesToNextcloud(event: MessageEvent){
        slackHandler.saveFiles(event).forEach{file ->
            nextcloudHandler.uploadFile(file.file.toFile(), file.channel, file.filename, file.user)
        }
    }
}