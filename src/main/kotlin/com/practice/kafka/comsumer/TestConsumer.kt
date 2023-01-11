package com.practice.kafka.comsumer

import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
@KafkaListener(
    topics = ["\${kafka-consumer.config.kafka-test.topic}"],
    clientIdPrefix = "\${kafka-consumer.config.kafka-test.client-id}",
    containerFactory = "testKafkaListenerContainerFactory"
)
class TestConsumer {
    @KafkaHandler
    fun listen(msg: String){
        println(msg)
    }
}