plugins {
    kotlin("jvm") version "1.6.10"   

    id("org.springframework.boot") version "2.6.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

sourceSets {
    main {
        java.srcDirs("src/main/spike/kafka")
    }

    test {
        java.srcDirs("src/test/spike/kafka")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation(kotlin("test"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("org.springframework.kafka:spring-kafka")
}

tasks {
    test {
        useJUnitPlatform()
    }
}

