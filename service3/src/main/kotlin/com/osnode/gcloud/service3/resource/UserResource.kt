package com.osnode.gcloud.service3.resource

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserResource(@Autowired private val restTemplate: OAuth2RestTemplate) {

    @RequestMapping("/me")
    @HystrixCommand(
            fallbackMethod = "defaultMe",
            commandProperties = [(HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"))]
    )
    fun me(): String {
        try {
            return restTemplate.getForObject("http://gcloud-service2/users/me", String::class.java)!!
        } catch (e: Exception) {
            println(e)
            throw e
        }
    }

    fun defaultMe(): String {
        return "Hello default from service 3"
    }
}