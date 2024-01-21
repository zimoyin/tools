import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

group = "com.github.zimoyin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application {
        mainClass = "com.github.zimoyin.ApplicationKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "tools"
            packageVersion = "1.0.0"

            // macOS 应用程序图标
            macOS {
                iconFile.set(project.file("icon.icns"))
            }
            // Windows 应用程序图标
            windows {
                iconFile.set(project.file("icon.ico"))
            }
            // Linux 应用程序图标
            linux {
                iconFile.set(project.file("icon.png"))
            }
        }
    }
}
