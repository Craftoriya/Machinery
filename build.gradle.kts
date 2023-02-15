import io.papermc.paperweight.tasks.RemapJar
import io.papermc.paperweight.util.convertToPath
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import xyz.jpenilla.runpaper.task.RunServer

plugins {
    kotlin("jvm") version "1.8.10"
    id("io.papermc.paperweight.userdev") version "1.4.0"
    id("xyz.jpenilla.run-paper") version "2.0.1" // Adds runServer and runMojangMappedServer tasks for testing
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2" // Generates plugin.yml
    id("com.github.johnrengelman.shadow") version "7.1.2"
}


group = "com.craftoriya"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    paperDevBundle("1.19.3-R0.1-SNAPSHOT")
    compileOnly("org.atteo.classindex:classindex:3.4")
    annotationProcessor("org.atteo.classindex:classindex:3.4")

    compileOnly("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")

    testCompileOnly("org.projectlombok:lombok:1.18.26")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.26")

    implementation(kotlin("reflect"))
    implementation(kotlin("scripting-common"))
    implementation(kotlin("scripting-jvm"))
    implementation(kotlin("scripting-dependencies"))
    implementation(kotlin("scripting-dependencies-maven"))

    implementation(kotlin("scripting-jvm-host-unshaded"))


    compileOnly(kotlin("stdlib"))

    implementation("org.jetbrains.kotlin:kotlin-scripting-jsr223:1.8.10")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    shadow("org.jetbrains.kotlin:kotlin-scripting-jsr223:1.8.10")
    shadow("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.withType(JavaCompile::class.java) {
    options.encoding = "UTF-8"
}

// Configure plugin.yml generation
bukkit {
    load = net.minecrell.pluginyml.bukkit.BukkitPluginDescription.PluginLoadOrder.STARTUP
    main = "com.craftoriya.machinery.MachineryAPI"
    apiVersion = "1.19"
    authors = listOf("NitkaNikita")
    name = "Machinery"
    commands {
        register("ping")
    }
}