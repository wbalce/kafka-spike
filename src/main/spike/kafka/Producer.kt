package spike.kafka

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.util.concurrent.ListenableFutureCallback
import org.springframework.util.concurrent.ListenableFuture
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Component

@Component
open class Producer(private val kafkaTemplate: KafkaTemplate<String, String>) {
    val topicName = "my-topic-name"

    open fun sendMessage(message: String) {
        println("Publishing message '$message' to topic '$topicName'")

        val future = kafkaTemplate.send(topicName, message)

        future.addCallback(object: ListenableFutureCallback<SendResult<String, String>> {
            override fun onSuccess(sendResult: SendResult<String, String>) {
                val message = sendResult.getProducerRecord().value()
                println("Successfully published message '$message' to topic '$topicName'")
            }

            override fun onFailure(e: Throwable) {
                println(
                    """
                        ============================================================
                        Unsuccessfully published message to topic '$topicName'
                        ============================================================
                    """.trimIndent()
                )
            }
        })
    }
}

