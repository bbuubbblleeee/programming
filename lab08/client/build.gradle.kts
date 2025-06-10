plugins {
    id("java")
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

application {
    mainClass.set("clientMain.ClientMain")
}

repositories {
    mavenCentral()
}
group = "org.example"
version = "1.0-SNAPSHOT"

javafx {
    version = "21" // Используйте LTS-версию
    modules = listOf("javafx.controls", "javafx.fxml")
}



dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.10.1")

    implementation(project(":common"))

}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE  // Исключить дубликаты
}
tasks.withType<JavaExec> {
    jvmArgs = listOf(
        "--module-path", "C:/Program Files/openjfx/javafx-sdk-24.0.1/lib",
        "--add-modules", "javafx.controls,javafx.fxml"
    )
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

//sourceSets {
//    main {
//        java {
//            srcDirs = "src/main/java"
//        }
//        resources {
//            srcDirs = ['src/main/resources']
//        }
//    }
//}

