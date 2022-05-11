// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.2")
        // classpath("com.google.dagger:hilt-android-gradle-plugin:2.41")
        // classpath("org.jlleitschuh.gradle:ktlint-gradle:10.2.1")
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.20.0")
    }
}

plugins {
    id("com.android.application") version ("7.1.1") apply false
    id("com.android.library") version ("7.1.1") apply false
    id("org.jetbrains.kotlin.android") version ("1.6.10") apply false
    id("org.jlleitschuh.gradle.ktlint") apply false
    id("dagger.hilt.android.plugin") apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
