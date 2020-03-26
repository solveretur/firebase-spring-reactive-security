package com.github.solveretur.firebase.spring.reactive.security.user

import reactor.core.publisher.Mono

interface UserService {
    fun findByEmail(email: String): Mono<AppUser>
}
