package com.github.solveretur.firebase.spring.reactive.security.security

import com.github.solveretur.firebase.spring.reactive.security.user.AppUser
import com.github.solveretur.firebase.spring.reactive.security.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class SpringSecurityService constructor(@Autowired val userService: UserService) : SecurityService {

    override fun login(username: String, password: String): Mono<AppUser> {
        return userService
            .findByEmail(username)
            .filter { SecurityService.matches(password, it.passwordHash) }
            .switchIfEmpty(Mono.defer { Mono.error<AppUser>(UserNotFoundException(username, password)) })
    }
}
