package com.osnode.gcloud.oauth2.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.*
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager


@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        val encodingId = "bcrypt"
        val encoders = HashMap<String?, PasswordEncoder>()
        encoders.put(encodingId, BCryptPasswordEncoder())
        encoders.put("ldap", LdapShaPasswordEncoder())
        encoders.put("MD4", Md4PasswordEncoder())
        encoders.put("MD5", MessageDigestPasswordEncoder("MD5"))
        encoders.put("noop", NoOpPasswordEncoder.getInstance())
        encoders.put("pbkdf2", Pbkdf2PasswordEncoder())
        encoders.put("scrypt", SCryptPasswordEncoder())
        encoders.put("SHA-1", MessageDigestPasswordEncoder("SHA-1"))
        encoders.put("SHA-256", MessageDigestPasswordEncoder("SHA-256"))
        encoders.put("sha256", StandardPasswordEncoder())
        encoders.put(null, NoOpPasswordEncoder.getInstance())
        return DelegatingPasswordEncoder(encodingId, encoders)
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean("inMemory")
    @Throws(Exception::class)
    override fun userDetailsServiceBean(): UserDetailsService {
        val manager = InMemoryUserDetailsManager()
        manager.createUser(User.withUsername("user").password("password").roles("USER").build())
        return manager
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsServiceBean()).passwordEncoder(passwordEncoder())
    }

    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/actuator").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/hystrix.stream").permitAll()
                .anyRequest().authenticated()
    }
}