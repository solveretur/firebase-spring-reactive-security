package com.github.solveretur.firebase.spring.reactive.security.security

import com.github.solveretur.firebase.spring.reactive.security.data.ReactiveDataService
import com.github.solveretur.firebase.spring.reactive.security.user.AppUser
import com.github.solveretur.firebase.spring.reactive.security.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service("securityService")
class SpringSecurityService constructor(
    private val userService: UserService,
    private val dataService: ReactiveDataService
) : SecurityService {

    override fun login(username: String, password: String): Mono<AppUser> {
        return userService
            .findByEmail(username)
            .filter { SecurityService.matches(password, it.passwordHash) }
            .switchIfEmpty(Mono.defer { Mono.error<AppUser>(UserNotFoundException(username, password)) })
    }

    override fun isOwner(id: String, currentUser: CurrentUser): Mono<Boolean> {
        return dataService.findById(id).map { it.appUserId == currentUser.getId() }.defaultIfEmpty(false)
    }

    override fun notReactiveIsOwner(id: String, currentUser: CurrentUser): Boolean {
        return isOwner(id, currentUser).block() ?: false
    }
}
