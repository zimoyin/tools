pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    plugins {
        kotlin("jvm").version(extra["kotlin.version"] as String)
        id("org.jetbrains.compose").version(extra["compose.version"] as String)

        println("> compose.version  : ${extra["compose.version"]}")
        println("> kotlin.version   : ${extra["kotlin.version"]}")
        println("> target.jvm.version : ${extra["target.jvm.version"]}")
        println("> Java Version     : ${ System.getProperty("java.version") } ")
        println("> Java VM Version  : ${ System.getProperty("java.vm.version") } ")
        println("> Java VM Vendor   : ${ System.getProperty("java.vm.vendor") }  ")
        println("> Java VM Name     : ${ System.getProperty("java.vm.name")  } ")
        println("> Java VM specification Version : ${ System.getProperty("java.vm.specification.version") }" )
        println("> Java VM specification Vendor  : ${ System.getProperty("java.vm.specification.vendor") }" )
        println("> Java VM specification Name    : ${ System.getProperty("java.vm.specification.name") }" )
        println("> Java Runtime specification Version : ${ System.getProperty("java.specification.version") }" )
        println("> Java Runtime specification Vendor  : ${ System.getProperty("java.specification.vendor") }" )
        println("> Java Runtime specification Name    : ${ System.getProperty("java.specification.name") }" )
        println("--------------------------------------------------------------------")
    }
}

rootProject.name = "tools"
