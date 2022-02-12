package spike.kafka

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.kafka.core.KafkaTemplate

@RestController
class Controller(private val kafkaTemplate: KafkaTemplate<String, String>) {
    val topicName = "my-topic-name"

    @PostMapping("publish/{message}")
    fun getAction(@PathVariable message: String) {
        println("Publishing message '$message' to topic '$topicName'")
        kafkaTemplate.send(topicName, message)
    }
}
