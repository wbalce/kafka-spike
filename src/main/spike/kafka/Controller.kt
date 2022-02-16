package spike.kafka

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(private val service: Service, private val consumer: Consumer) {

    @PostMapping("publish/{message}")
    fun getAction(@PathVariable message: String) {
        service.sendMessage(message)
    }
}

