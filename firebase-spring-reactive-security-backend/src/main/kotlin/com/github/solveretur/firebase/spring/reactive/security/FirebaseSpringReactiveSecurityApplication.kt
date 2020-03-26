package com.github.solveretur.firebase.spring.reactive.security

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.time.Clock

@SpringBootApplication
class FirebaseSpringReactiveSecurityApplication

fun main(args: Array<String>) {
	runApplication<FirebaseSpringReactiveSecurityApplication>(*args)
}
