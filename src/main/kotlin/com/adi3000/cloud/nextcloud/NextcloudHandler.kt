package com.adi3000.cloud.nextcloud

import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.aarboard.nextcloud.api.NextcloudConnector
import java.io.File

//TODO implement endpoint to register a password
// cf. https://docs.nextcloud.com/server/latest/developer_manual/client_apis/LoginFlow/index.html#converting-to-app-passwords
@Singleton
class NextcloudHandler (@Inject val nextcloudConfig: NextcloudConfiguration){

    fun uploadFile(file: File, directory:String, filename: String, userId: String ){
        val user = nextcloudConfig.properties.users.first{ it.user == userId }
        val connector = NextcloudConnector(nextcloudConfig.properties.server, user.login, user.password)
        if ( ! connector.folderExists(nextcloudConfig.properties.folder)) {
            connector.createFolder(nextcloudConfig.properties.folder)
        }
        if (! connector.folderExists("${nextcloudConfig.properties.folder}/$directory")){
            connector.createFolder("${nextcloudConfig.properties.folder}/$directory")
        }
        connector.uploadFile(file, "${nextcloudConfig.properties.folder}/$directory/$filename")
    }
}