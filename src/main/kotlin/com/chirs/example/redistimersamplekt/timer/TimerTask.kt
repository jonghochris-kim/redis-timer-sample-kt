package com.chirs.example.redistimersamplekt.timer

import com.chirs.example.redistimersamplekt.pubusb.PubsubSender
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import java.io.Serializable
import java.time.LocalDateTime

class TimerTask: Runnable, Serializable {

//    @Autowired
//    private val applicationEventPublisher: ApplicationEventPublisher? = null

    @Autowired
    private val pubsubSender: PubsubSender? = null

    private var timer: Timer? = null

    constructor()
    constructor(timer: Timer?) {
        this.timer = timer
    }

    override fun run() {
        println("TimerTask#run >>>> " + LocalDateTime.now())
        println("TimerTask#run.message >>>>" + timer?.message)
        pubsubSender?.sendOrderExpireArrivalNoti(timer?.message ?: "direct message !!")
//        applicationEventPublisher!!.publishEvent(TimerEvent(timer?.id, timer?.message))
    }
}