package com.github.solveretur.firebase.spring.reactive.security.user

import reactor.core.publisher.Mono

interface UserService {
    fun findByEmail(email: String): Mono<AppUser>
    fun findById(id: String): Mono<AppUser>
    fun create(id: String, email: String): Mono<AppUser>
}
