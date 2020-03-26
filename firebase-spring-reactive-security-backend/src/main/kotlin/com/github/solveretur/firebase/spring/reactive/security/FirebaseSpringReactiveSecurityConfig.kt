package com.github.solveretur.firebase.spring.reactive.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Clock

@Configuration
class FirebaseSpringReactiveSecurityConfig {

    @Bean
    fun defaultClock(): Clock {
        return Clock.systemDefaultZone()
    }
}