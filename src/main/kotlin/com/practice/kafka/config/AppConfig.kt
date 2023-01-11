package com.practice.kafka.config

import com.practice.kafka.config.kafka.KafkaConsumerProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties
data class AppConfig(
    val kafkaConsumer: KafkaConsumerProperties
)