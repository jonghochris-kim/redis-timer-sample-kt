package com.chirs.example.redistimersamplekt.controller

import com.chirs.example.redistimersamplekt.timer.RegisterTimer
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/redis-timer/messages")
class RedissonMessageController(
    private val registerTimer: RegisterTimer
) {

    @PostMapping("/register/timers/{message}")
    fun registerTimer(@PathVariable message: String): Mono<*>? {
        registerTimer.registerTimer(message)
        return Mono.just(message)
    }
}