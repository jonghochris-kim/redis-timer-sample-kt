package com.chirs.example.redistimersamplekt.pubusb

import com.chirs.example.redistimersamplekt.pubusb.init.InitializePubsub
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

const val TOPIC_ORDER_EXPIRE_ARRIVAL_NOTI = "carmerce-order-expire-arrival-noti"

@Service
class PubsubSender(
    private val pubSubTemplate: PubSubTemplate,
    private val initializePubsub: InitializePubsub
) {

    val envName: String = "local"

    @PostConstruct
    fun postConstruct() {
        initializePubsub.initializeSubscription(TOPIC_ORDER_EXPIRE_ARRIVAL_NOTI)
    }

    fun sendOrderExpireArrivalNoti(s: String) {
        println("sendOrderExpireArrivalNoti")
        val attrMap = mapOf("env" to envName, "version" to "v1")
        pubSubTemplate.publish(TOPIC_ORDER_EXPIRE_ARRIVAL_NOTI, s, attrMap)
    }
}