package com.craftoriya.machinery

import com.craftoriya.machinery.scripting.ScriptWithMavenDeps
import org.bukkit.plugin.java.JavaPlugin
import kotlin.script.experimental.host.toScriptSource
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost
import kotlin.script.experimental.jvmhost.createJvmCompilationConfigurationFromTemplate


lateinit var plugin: MachineryAPI

class MachineryAPI : JavaPlugin() {


    override fun onEnable() {
        plugin = this;


        if (!dataFolder.exists()) dataFolder.mkdir()
        Thread.currentThread().contextClassLoader = this.classLoader;
        dataFolder.listFiles().forEach {
            logger.info(it.path.toString())
            val compilationConfiguration = createJvmCompilationConfigurationFromTemplate<ScriptWithMavenDeps>()
            var eval = BasicJvmScriptingHost().eval(it.toScriptSource(), compilationConfiguration, null)
            eval.reports.forEach {
                logger.warning(it.render(withStackTrace = true, withLocation = true))
            }
        }

    }

    fun helloWorld() {
        logger.info("HELLO FROM SCRIPT")
    }
}