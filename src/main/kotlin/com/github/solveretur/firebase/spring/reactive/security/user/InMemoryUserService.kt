package com.github.solveretur.firebase.spring.reactive.security.user

import com.github.solveretur.firebase.spring.reactive.security.security.SecurityService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.time.Clock
import java.time.LocalDateTime

@Service
class InMemoryUserService(private val clock: Clock) : UserService {

    private val users = setOf(
        AppUser("user1", "admin@gmail.com", SecurityService.encode("admin"), AccessRole.ROLE_ADMIN, LocalDateTime.now(clock), false, LocalDateTime.now(clock)),
        AppUser("user2", "user@gmail.com", SecurityService.encode("user"), AccessRole.ROLE_USER, LocalDateTime.now(clock), false, LocalDateTime.now(clock)),
        AppUser("user3", "other@gmail.com", SecurityService.encode("other"), AccessRole.ROLE_USER, LocalDateTime.now(clock), false, LocalDateTime.now(clock))
    )


    override fun findByEmail(email: String): Mono<AppUser> {
        return Mono.justOrEmpty(users.find { it.email == email })
    }
}
