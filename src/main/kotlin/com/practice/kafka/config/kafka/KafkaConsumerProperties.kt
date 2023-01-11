package com.practice.kafka.config.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

// https://kafka.apache.org/documentation/#consumerconfigs
data class KafkaConsumerProperties(
    val config: Map<String, KafkaConsumerConfig>,
    val keyDeserializerClass: Class<*> = StringDeserializer::class.java,
    val valueDeserializerClass: Class<*> = StringDeserializer::class.java,
    val autoOffsetReset: String = "earliest", // 가장 최초의 오프셋값으로 설정
) {
    data class KafkaConsumerConfig(
        val bootstrapServers: String,
        val clientId: String,
        val groupId: String,    // 필요시 사용
    )

    fun propertiesMap(kafkaConsumerConfig: KafkaConsumerConfig): Map<String, Any?> =
        mutableMapOf<String, Any?>(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaConsumerConfig.bootstrapServers,
            ConsumerConfig.CLIENT_ID_CONFIG to kafkaConsumerConfig.clientId,
            ConsumerConfig.GROUP_ID_CONFIG to kafkaConsumerConfig.groupId,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to keyDeserializerClass,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to valueDeserializerClass,
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to autoOffsetReset,
        ).toMap()

    fun <V> consumerFactory(key: String): ConsumerFactory<String, V> {
        return DefaultKafkaConsumerFactory(propertiesMap(config[key]!!))
    }
}
