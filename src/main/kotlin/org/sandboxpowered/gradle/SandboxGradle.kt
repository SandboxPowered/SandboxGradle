package org.sandboxpowered.gradle

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import java.net.URI

class SandboxGradle : Plugin<Project> {
    override fun apply(project: Project) {
        project.apply(
            mapOf(
                "plugin" to "java"
            )
        )

        project.extensions.create("sandbox", SandboxExtension::class.java, project)

        project.afterEvaluate {
            it.extensions.getByType(JavaPluginExtension::class.java).apply {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }
        }

        project.repositories.apply {
            mavenCentral()
            jcenter()
            maven { it.url = URI("https://nexus.sandboxpowered.org/repository/maven-public/") }
        }
    }
}