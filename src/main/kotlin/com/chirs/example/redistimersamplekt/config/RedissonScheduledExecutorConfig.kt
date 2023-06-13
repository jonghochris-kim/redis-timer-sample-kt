package com.chirs.example.redistimersamplekt.config

import org.redisson.api.ExecutorOptions
import org.redisson.api.RScheduledExecutorService
import org.redisson.api.RedissonClient
import org.redisson.api.WorkerOptions
import org.springframework.beans.factory.BeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
class RedissonScheduledExecutorConfig {

    val EXECUTOR_SERVICE_NAME = "rScheduledExecutorKotlin"

    @Bean
    fun rScheduledExecutorService(
        redissonClient: RedissonClient,
        beanFactory: BeanFactory?
    ): RScheduledExecutorService? {
        val workerOptions = WorkerOptions.defaults().workers(2).beanFactory(beanFactory)
        val executorOptions = ExecutorOptions.defaults().taskRetryInterval(3, TimeUnit.SECONDS)
        val rScheduledExecutorService =
            redissonClient.getExecutorService(EXECUTOR_SERVICE_NAME, executorOptions)
        rScheduledExecutorService.registerWorkers(workerOptions)
        return rScheduledExecutorService
    }
}