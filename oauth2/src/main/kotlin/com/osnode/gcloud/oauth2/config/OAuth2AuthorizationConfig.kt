package com.osnode.gcloud.oauth2.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory


@Configuration
@EnableAuthorizationServer
class OAuth2AuthorizationConfig(@Autowired private val authenticationManager: AuthenticationManager, @Autowired @Qualifier("inMemory") private val userDetailsService: UserDetailsService) : AuthorizationServerConfigurerAdapter() {

    @Bean
    fun jwtAccessTokenConverter(): JwtAccessTokenConverter {
        val converter = JwtAccessTokenConverter()
        val keyPair = KeyStoreKeyFactory(ClassPathResource("keystore.jks"), "foobar".toCharArray()).getKeyPair("test")
        converter.setKeyPair(keyPair)
        return converter
    }

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients
                .inMemory()
                .withClient("acme")
                .secret("acmesecret")
                .authorities("ROLE_TRUSTED_CLIENT")
                .authorizedGrantTypes("implicit", "authorization_code", "refresh_token", "password")
                .accessTokenValiditySeconds(600)
                .refreshTokenValiditySeconds(6000)
                .scopes("openid")
                .autoApprove(true)
    }

    override fun configure(security: AuthorizationServerSecurityConfigurer) {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints
                .authenticationManager(authenticationManager)
                .accessTokenConverter(jwtAccessTokenConverter())
                .userDetailsService(userDetailsService)
    }
}