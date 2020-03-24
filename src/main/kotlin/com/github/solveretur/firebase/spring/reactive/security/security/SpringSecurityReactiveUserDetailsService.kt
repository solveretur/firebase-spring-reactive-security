package com.github.solveretur.firebase.spring.reactive.security.security

import com.github.solveretur.firebase.spring.reactive.security.user.UserService
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import reactor.core.publisher.Mono

class SpringSecurityReactiveUserDetailsService(private val userService: UserService) : ReactiveUserDetailsService {

    override fun findByUsername(username: String?): Mono<UserDetails> {
        return Mono
                .justOrEmpty(username)
                .flatMap { userService.findByEmail(it) }
                .filter { !it.isDisabled }
                .map { CurrentUser(it) }
    }
}
