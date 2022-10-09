package com.adi3000.cloud.bridge

import org.yaml.snakeyaml.Yaml
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

open class BridgeConfiguration<T> (val filePath:String){
    companion object {
        val classpathScheme= "classpath:"
    }

    protected fun loadProperties(clazz:Class<T>): T{
        val usersYaml = Yaml()
        val file = Files.newInputStream(getPath(filePath), StandardOpenOption.READ)
        val properties = usersYaml.loadAs(file, clazz)
        file.close()
        return properties
    }

    private fun getPath(filePath: String): Path =
        if (filePath.startsWith(classpathScheme)){
            Paths.get(ClassLoader.getSystemResource(filePath.drop(classpathScheme.length)).toURI())
        }else{
            Paths.get(filePath)
        }

}