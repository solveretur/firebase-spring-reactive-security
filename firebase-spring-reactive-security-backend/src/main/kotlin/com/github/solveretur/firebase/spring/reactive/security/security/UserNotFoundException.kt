package com.github.solveretur.firebase.spring.reactive.security.security

class UserNotFoundException(username: String, password: String) : RuntimeException("User with username: $username and password $password not found")
