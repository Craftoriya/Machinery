package com.craftoriya.machinery.scripting

import com.craftoriya.machinery.MachineryAPI
import org.bukkit.Bukkit
import org.slf4j.LoggerFactory
import kotlin.script.experimental.annotations.KotlinScript
import kotlin.script.experimental.api.*
import kotlin.script.experimental.dependencies.DependsOn
import kotlin.script.experimental.dependencies.Repository
import kotlin.script.experimental.jvm.dependenciesFromClassContext
import kotlin.script.experimental.jvm.dependenciesFromCurrentContext
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.jvm.util.classpathFromClass

@KotlinScript(
    // file name extension by which this script type is recognized by mechanisms built into scripting compiler plugin
    // and IDE support, it is recommendend to use double extension with the last one being "kts", so some non-specific
    // scripting support could be used, e.g. in IDE, if the specific support is not installed.
    fileExtension = "mc.kts",
    // the class or object that defines script compilation configuration for this type of scripts
    compilationConfiguration = ScriptWithMavenDepsConfiguration::class
)
// the class is used as the script base class, therefore it should be open or abstract
abstract class ScriptWithMavenDeps {
}

object ScriptWithMavenDepsConfiguration : ScriptCompilationConfiguration(
    {
        // adds implicit import statements (in this case `implort kotlin.script.experimental.dependencies.DependsOn`, etc.)
        // to each script on compilation
        defaultImports(DependsOn::class, Repository::class, MachineryAPI::class)


        jvm {
            classpathFromClass(this::class)

            // the dependenciesFromCurrentContext helper function extracts the classpath from current thread classloader
            // and take jars with mentioned names to the compilation classpath via `dependencies` key.
            // to add the whole classpath for the classloader without check for jar presense, use
            // `dependenciesFromCurrentContext(wholeClasspath = true)`
        }
        ide {
            acceptedLocations(ScriptAcceptedLocation.Everywhere)
        }
    }
)

