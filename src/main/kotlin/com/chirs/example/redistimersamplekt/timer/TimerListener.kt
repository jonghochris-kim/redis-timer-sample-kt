package com.chirs.example.redistimersamplekt.timer

import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class TimerListener {

    @Async
    @EventListener
    fun timerListener(timerEvent: TimerEvent) {
        println("TimerListener#timerListener >>>> ${timerEvent.message}")
    }
}