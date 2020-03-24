package com.github.solveretur.firebase.spring.reactive.security.security

import com.github.solveretur.firebase.spring.reactive.security.user.AppUser
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import reactor.core.publisher.Mono

interface SecurityService {
    fun login(username: String, password: String): Mono<AppUser>

    companion object {
        private val passwordEncoder: PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
        fun encode(password: String): String {
            return passwordEncoder.encode(password)
        }

        fun matches(password: String, otherPasswordHash: String): Boolean {
            return passwordEncoder.matches(password, otherPasswordHash)
        }

    }
}
