package com.github.solveretur.firebase.spring.reactive.security.data

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Clock
import java.time.LocalDateTime

data class Data(
    val id: String,
    val appUserId: String,
    val data: String,
    val insertTimestamp: LocalDateTime
)

interface ReactiveDataService {
    fun findById(id: String): Mono<Data>
    fun findAllByAppUserId(appUserId: String): Flux<Data>
    fun save(data: Data): Mono<Data>
}

@Service
class InMemoryDataService(private val clock: Clock) : ReactiveDataService {
    private val data = mutableSetOf(
        Data("1234-5678-9101", "user1", "data1", LocalDateTime.now(clock)),
        Data("9101-5678-1234", "user1", "data2", LocalDateTime.now(clock)),
        Data("5678-9101-1234", "user3", "data3", LocalDateTime.now(clock)),
        Data("1098-7654-3210", "user3", "data4", LocalDateTime.now(clock)),
        Data("7654-1098-3210", "admin", "data5", LocalDateTime.now(clock)),
        Data("1234-3210-7654", "user1", "data6", LocalDateTime.now(clock)),
        Data("5678-5678-9101", "admin", "data7", LocalDateTime.now(clock)),
        Data("1234-5678-4311", "admin", "data8", LocalDateTime.now(clock)),
        Data("1234-1098-1234", "user2", "data9", LocalDateTime.now(clock)),
        Data("1234-1234-1234", "user3", "data10", LocalDateTime.now(clock)),
        Data("1234-5678-3210", "user3", "data11", LocalDateTime.now(clock)
        )
    )

    override fun findById(id: String): Mono<Data> {
        return Mono.defer {
            Mono.justOrEmpty(data.find { it.id == id })
        }
    }

    override fun findAllByAppUserId(appUserId: String): Flux<Data> {
        return Flux.defer { Flux.fromIterable(data) }.filter { it.appUserId == appUserId }
    }

    override fun save(data: Data): Mono<Data> {
        this.data.add(data)
        return Mono.defer { Mono.just(data) }
    }
}
