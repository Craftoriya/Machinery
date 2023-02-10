plugins {
    `java-library`
    kotlin("jvm") version "1.8.10"
    id("io.papermc.paperweight.userdev") version "1.4.0"
    id("xyz.jpenilla.run-paper") version "2.0.1" // Adds runServer and runMojangMappedServer tasks for testing
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2" // Generates plugin.yml
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
    implementation(kotlin("script-runtime"))
    implementation(kotlin("compiler-embeddable"))
    implementation(kotlin("script-util"))
    implementation(kotlin("scripting-compiler-embeddable"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

// Configure plugin.yml generation
bukkit {
    load = net.minecrell.pluginyml.bukkit.BukkitPluginDescription.PluginLoadOrder.STARTUP
    main = "com.craftoriya.paper.machinery.MachineryAPI"
    apiVersion = "1.19"
    authors = listOf("NitkaNikita")
    name = "Machinery"
}