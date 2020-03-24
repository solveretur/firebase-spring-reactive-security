package com.github.solveretur.firebase.spring.reactive.security.user

import java.time.LocalDateTime

data class AppUser(
    val id: String,
    val email: String,
    val passwordHash: String,
    val role: AccessRole,
    val creationDate: LocalDateTime,
    val isDisabled: Boolean = false,
    val lastUpdateTimestamp: LocalDateTime
)
