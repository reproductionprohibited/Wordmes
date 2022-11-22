import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat


plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        javaHome = System.getenv("JDK_15")
        nativeDistributions {
            windows {
                iconFile.set(project.file("icon.ico"))
            }

            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)

            packageName = "Wordmes"
            packageVersion = "1.0.0"

            includeAllModules = true
            appResourcesRootDir.set(project.layout.projectDirectory.dir("resources"))
            outputBaseDir.set(project.buildDir.resolve("prep_for_release_directory_1"))
        }
    }
}

