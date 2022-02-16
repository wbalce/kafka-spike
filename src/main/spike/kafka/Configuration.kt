package spike.kafka

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean
import org.springframework.kafka.core.KafkaAdmin
import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.annotation.EnableKafka
import org.apache.kafka.common.serialization.StringDeserializer

@Configuration
open class KafkaConfig {
    val bootstrapServerAddress = "http://localhost:9092"
    val groupId = "my-consumer-group-id"

    @Bean
    open fun admin() = KafkaAdmin(
        mapOf(
            AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServerAddress
        )
    )

    @Bean
    open fun producerFactory() = DefaultKafkaProducerFactory<String, String>(
        mapOf(
             ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServerAddress,
             ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
             ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java
        )
    )

    @Bean
    open fun template() = KafkaTemplate<String, String>(producerFactory())

    @Bean
    open fun consumerFactory() = DefaultKafkaConsumerFactory<String, String>(
        mapOf(
	     ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServerAddress,
             ConsumerConfig.GROUP_ID_CONFIG to groupId,
             ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
             ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java
        )
    )

    @Bean
    open fun kafkaListenerContainerFactory():
        ConcurrentKafkaListenerContainerFactory<String, String> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, String>()
        factory.setConsumerFactory(consumerFactory())
        return factory
    }
}

