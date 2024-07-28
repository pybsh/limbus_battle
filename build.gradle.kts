import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    alias(libs.plugins.kotlin)
    alias(libs.plugins.runPaper)
    alias(libs.plugins.pluginYml)
}

group = "me.pybsh"
version = "0.0.1"
val codeName = "limbusbattle"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    library(kotlin("stdlib"))
    compileOnly(libs.paper)

    compileOnly(libs.cloud)
    compileOnly(libs.coroutines)
    compileOnly(libs.mccoroutines)
    compileOnly(libs.mccoroutinesCore)

    paperLibrary(libs.cloud)
    paperLibrary(libs.coroutines)
    paperLibrary(libs.mccoroutines)
    paperLibrary(libs.mccoroutinesCore)
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_21.toString()
    }
    jar {
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("")
        archiveVersion.set("")
    }
    runServer {
        minecraftVersion(libs.versions.minecraft.get())
        jvmArgs = listOf("-Dcom.mojang.eula.agree=true")
    }
}

idea {
    module {
        excludeDirs.addAll(listOf(file("run"), file("out"), file(".idea")))
    }
}

paper {
    name = rootProject.name
    version = rootProject.version.toString()

    main =
        "${project.group}.${codeName}.plugin.LimbusBattlePlugin"
    loader = "${project.group}.${codeName}.plugin.loader.LimbusBattlePluginLoader"

    apiVersion = libs.paper.get().version!!.replace("-R0.1-SNAPSHOT", "")

    generateLibrariesJson = true
    foliaSupported = false
}
