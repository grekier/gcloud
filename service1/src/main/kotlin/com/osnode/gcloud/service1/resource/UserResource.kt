package com.osnode.gcloud.service1.resource

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserResource {

    @RequestMapping("/me")
    fun me():String {
        return SecurityContextHolder.getContext().getAuthentication().getName()
    }
}