package spike.kafka

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean
import org.springframework.kafka.core.KafkaAdmin
import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.kafka.core.KafkaTemplate

@Configuration
open class KafkaConfiguration {
    val bootstrapServerAddress = "http://localhost:9092"

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
}

