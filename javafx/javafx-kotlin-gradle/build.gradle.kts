plugins {
    kotlin("jvm") version "2.0.20"
    application
    id("org.javamodularity.moduleplugin") version "1.8.15"
}

group = "com.mathswe"
version = "1.0-SNAPSHOT"

application {
    mainModule.set("com.mathswe.app")
    mainClass.set("com.mathswe.app.MainKt")
}

kotlin {
    jvmToolchain(21)
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
