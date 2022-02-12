package spike.kafka

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
open class Application {

    @Bean
    open fun init() {
        println(
            """
                ============================================================
                Kafka Spike 
                ============================================================
            """.trimIndent()
        ) 
    }
}

fun main(args: Array<String>) { 
    runApplication<Application>(*args)
}
