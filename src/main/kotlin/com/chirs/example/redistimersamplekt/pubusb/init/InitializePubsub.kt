package com.chirs.example.redistimersamplekt.pubusb.init

import com.google.cloud.pubsub.v1.SubscriptionAdminClient
import com.google.pubsub.v1.ProjectSubscriptionName
import com.google.pubsub.v1.ProjectTopicName
import com.google.pubsub.v1.Subscription
import org.springframework.context.annotation.Configuration

@Configuration
class InitializePubsub(
    private val subscriptionAdminClient: SubscriptionAdminClient
) {

    private val gcpProjectId = "handle-304204"
    private val envName = "local"
    private val domainName = "timer"
    private val version = "v1"

    // Subscription 초기화
    fun initializeSubscription(topicName: String): String {
        val projectTopicName = ProjectTopicName.of(gcpProjectId, topicName)
        val subscriptionName = ProjectSubscriptionName.of(gcpProjectId, topicName.toSubscriptionName())
        try {
            subscriptionAdminClient.createSubscription(
                Subscription.newBuilder()
                    .setName(subscriptionName.toString())
                    .setTopic(projectTopicName.toString())
                    .setFilter("attributes.env=\"${envName}\" AND attributes.version=\"${version}\"")
                    .build()
            )
        } catch (th: Throwable) {
            println(th.message)
        }

        return subscriptionName.toString()
    }

    // Subscription 명 생성
    private fun String.toSubscriptionName(): String {
        return "$this-${domainName}-${envName}-${version}"
    }
}