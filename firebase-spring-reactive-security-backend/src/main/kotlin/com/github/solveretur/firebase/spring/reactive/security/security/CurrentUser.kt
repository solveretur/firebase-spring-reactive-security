package com.github.solveretur.firebase.spring.reactive.security.security

import com.github.solveretur.firebase.spring.reactive.security.user.AccessRole
import com.github.solveretur.firebase.spring.reactive.security.user.AppUser
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User

class CurrentUser constructor(val appUser: AppUser) : User(appUser.email, appUser.passwordHash, AuthorityUtils.createAuthorityList(appUser.role.toString())) {

    fun getId(): String {
        return appUser.id
    }

    fun getRole(): AccessRole {
        return appUser.role
    }

    fun getEmail(): String {
        return appUser.email
    }
}
