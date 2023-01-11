package com.practice.kafka.config.kafka

import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.listener.CommonErrorHandler
import org.springframework.kafka.listener.MessageListenerContainer

@Configuration
class KafkaConsumerErrorHandler : CommonErrorHandler {
    override fun handleRecord(
        thrownException: Exception,
        record: ConsumerRecord<*, *>,
        consumer: Consumer<*, *>,
        container: MessageListenerContainer
    ) {
        LOGSTASH_KAFKA_ERROR_LOGGER.error(thrownException.message)
    }

    companion object {
        val LOGSTASH_KAFKA_ERROR_LOGGER: Logger = LoggerFactory.getLogger("LogstashKafka")
    }
}