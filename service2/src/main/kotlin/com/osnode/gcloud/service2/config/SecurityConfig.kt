package com.osnode.gcloud.service2.config

import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer


@Configuration
@EnableWebSecurity
@EnableResourceServer
class SecurityConfig: WebSecurityConfigurerAdapter(), ResourceServerConfigurer {

    override fun configure(resources: ResourceServerSecurityConfigurer) {
        resources.resourceId("service2")
    }

    override fun configure(http: HttpSecurity) {
        http
                .httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/actuator").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated()
    }

}