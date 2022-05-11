pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()

        resolutionStrategy {
            eachPlugin {
                when (requested.id.id) {
                    "dagger.hilt.android.plugin" -> {
                        useModule("com.google.dagger:hilt-android-gradle-plugin:2.41")
                    }
                    "org.jlleitschuh.gradle.ktlint" -> {
                        useModule("org.jlleitschuh.gradle:ktlint-gradle:10.2.1")
                    }
                }
            }
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

include(":app")
rootProject.name = "RickAndMorty-GraphQL"
