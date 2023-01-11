package com.practice.kafka.config.kafka

import com.practice.kafka.config.AppConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory

@Configuration
class KafkaConsumerConfig(
    private val kafkaConsumerErrorHandler: KafkaConsumerErrorHandler,
) {
    @Bean("testKafkaListenerContainerFactory")
    fun testKafkaListenerContainerFactory(
        appConfig: AppConfig
    ): ConcurrentKafkaListenerContainerFactory<String, String> =
        ConcurrentKafkaListenerContainerFactory<String, String>().apply {
            consumerFactory = appConfig.kafkaConsumer.consumerFactory(KAFKA_TEST)
            setCommonErrorHandler(kafkaConsumerErrorHandler)
        }

    companion object {
        const val KAFKA_TEST= "kafka-test"
    }
}


