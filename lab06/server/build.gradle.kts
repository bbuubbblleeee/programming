plugins {
    id("java")
    application
    id("com.github.johnrengelman.shadow") version "7.1.2"
}


application {
    mainClass.set("main.ServerMain")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


dependencies {
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.12.1")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(project(":common"))
}

tasks.test {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}



