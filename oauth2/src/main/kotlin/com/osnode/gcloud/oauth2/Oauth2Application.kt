package com.osnode.gcloud.oauth2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class Oauth2Application

fun main(args: Array<String>) {
    runApplication<Oauth2Application>(*args)
}
