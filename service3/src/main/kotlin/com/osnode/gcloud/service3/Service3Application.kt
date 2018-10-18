package com.osnode.gcloud.service3

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.security.oauth2.client.OAuth2ClientContext
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
class Service3Application{

    @LoadBalanced
    @Bean
    fun loadBalancedOauth2RestTemplate(resource: OAuth2ProtectedResourceDetails, context: OAuth2ClientContext): OAuth2RestTemplate {
        return OAuth2RestTemplate(resource, context)
    }
}

fun main(args: Array<String>) {
    runApplication<Service3Application>(*args)
}
