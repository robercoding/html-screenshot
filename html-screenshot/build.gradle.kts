import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
    `maven-publish`
}

group = "dev.robercoding"
version = "1.0.0-alpha02"

kotlin {
    androidTarget {
        publishLibraryVariants("release")
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {

        androidMain.dependencies {
            implementation(libs.androidx.startup.runtime)

        }
        val commonMain by getting {
            dependencies {
                api(libs.kotlinx.coroutines.core)
            }
        }

        // val iosMain by creating {
        //     dependsOn(commonMain)
        // }
        // val iosSimulatorArm64Main by getting {
        //     dependsOn(iosMain)
        // }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

android {
    namespace = "org.jetbrains.kotlinx.multiplatform.library.template"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
dependencies {
    implementation(libs.androidx.webkit)
}


publishing {
    publications.withType<MavenPublication>().configureEach {
        groupId = project.group.toString()

        artifactId = when (this.name) {
            "kotlinMultiplatform" -> project.name
            else -> {
                // source: https://github.com/orgs/community/discussions/26328#discussioncomment-3251482
                // artifactId should be lowercase to be able to push to github packages
                // This is a workaround to publish on GitHub Packages with lowercase artifactId
                // strip "Publication", insert dashes between camel-case transitions, lowercase
                // e.g: iosArm64Publication -> ios-arm64
                // e.g: iosX64Publication -> ios-x64
                // e.g: iosSimulatorArm64Publication -> ios-simulator-arm64
                val suffix = name
                    .removeSuffix("Publication")
                    .replace(Regex("([a-z])([A-Z])"), "$1-$2")
                    .lowercase()
                "${project.name}-$suffix"
            }
        }

        version = project.version.toString()
    }

    // Publish
    repositories {
        maven {
            name = "GitHubPackages"
            url  = uri("https://maven.pkg.github.com/robercoding/html-screenshot-kmm")
            // gradle property htmlScreenshot.githubActor
            val githubActor = System.getenv("GITHUB_ACTOR") ?: project.findProperty("htmlScreenshot.githubActor") as String
            val githubToken = System.getenv("GITHUB_TOKEN") ?: project.findProperty("htmlScreenshot.githubToken") as String
            credentials {
                username = githubActor
                password = githubToken
            }
        }
    }
}

