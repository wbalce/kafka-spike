package spike.kafka

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import org.springframework.kafka.core.KafkaTemplate

@Component
open class Consumer {

    @KafkaListener(topics = arrayOf("my-topic-name"))
    fun consume(message: String) {
        println(
            """
                ============================================================
                Received message: $message
                ============================================================
            """.trimIndent()
        )
    }
}

