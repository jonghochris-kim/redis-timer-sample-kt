package com.chirs.example.redistimersamplekt.timer

import org.redisson.api.RScheduledExecutorService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.TimeUnit

@Service
class RegisterTimer(
    private val rScheduledExecutorService: RScheduledExecutorService
) {

    fun registerTimer(message: String?) {
        // Executor 가 종료된 상태이면 재등록될 수 있도록 종료시킨다.
        if (rScheduledExecutorService.isShutdown) {
            rScheduledExecutorService.delete()
        }
        println("RegisterTimer#registerTimer >>>> " + LocalDateTime.now())

        // delay 시간 뒤에 Redis Scheduler 가 태스크를 작동시킨다.
        rScheduledExecutorService.schedule(
            TimerTask(Timer(UUID.randomUUID().toString(), 3, message)),
            3L,
            TimeUnit.SECONDS
        )
    }
}