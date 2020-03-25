package com.github.solveretur.firebase.spring.reactive.security.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.solveretur.firebase.spring.reactive.security.security.CurrentUser
import com.github.solveretur.firebase.spring.reactive.security.security.SecurityService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import reactor.core.publisher.Mono

@Controller
@RequestMapping("/data")
class DataController(
    private val dataService: ReactiveDataService
) {

    @GetMapping
    fun display(@AuthenticationPrincipal currentUser: CurrentUser): Mono<ResponseEntity<DataResponse>> {
        return dataService
            .findAllByAppUserId(currentUser.getId())
            .collectList()
            .map { ResponseEntity.ok(DataResponse(it)) }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{dataId}")
    fun displayById(@AuthenticationPrincipal currentUser: CurrentUser, @PathVariable dataId: String): Mono<ResponseEntity<Data>> {
        return dataService
            .findById(dataId)
            .map { ResponseEntity.ok(it) }
    }

}

data class DataResponse(
    @get:JsonProperty("data_list")
    val dataList: List<Data> = emptyList()
)
