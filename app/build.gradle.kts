plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
    id("org.jlleitschuh.gradle.ktlint")
    id("io.gitlab.arturbosch.detekt")
    id("com.apollographql.apollo3") version ("3.1.0")
}

android {
    compileSdk = AndroidBuild.COMPILE_SDK

    defaultConfig {
        applicationId = AndroidBuild.APPLICATION_ID
        minSdk = AndroidBuild.MIN_ANDROID_SDK
        targetSdk = AndroidBuild.TARGET_ANDROID_SDK
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = AndroidBuild.TEST_INSTRUMENTATION_RUNNER
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.APP_COMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAINT_LAYOUT)

    // Navigation Component
    implementation(Dependencies.NAVIGATION_FRAGMENT_KTX)
    implementation(Dependencies.NAVIGATION_UI_KTX)

    // Retrofit
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER)
    implementation(Dependencies.RETROFIT_ADAPTER)

    // grapghql
    implementation(Dependencies.APOLLO_RUNTIME)

    // Room
    implementation(Dependencies.ROOM_RUNTIME)
    kapt(Dependencies.ROOM_COMPILER)
    implementation(Dependencies.ROOM_KTX)
    implementation(Dependencies.ROOM_PAGING)

    // Okhttp
    implementation(Dependencies.OKHTTP)
    implementation(Dependencies.LOGGING)

    // Coil
    implementation(Dependencies.COIL)

    // Coroutines
    implementation(Dependencies.COROUTINES_CORE)
    implementation(Dependencies.COROUTINES_ANDROID)

    // Hilt
    implementation(Dependencies.DAGGER_HILT)
    kapt(Dependencies.DAGGER_HILT_COMPILER)

    // Paging
    implementation(Dependencies.PAGING_RUNTIME)

    // Timber
    implementation(Dependencies.TIMBER)

    implementation(Dependencies.INTUIT_SSP) {
        because("An android lib that provides a new size unit - ssp (scalable sp). This size unit scales with the screen size based on the sp size unit (for texts). It can help Android developers with supporting multiple screens.")
    }
    implementation(Dependencies.INTUIT_SDP) {
        because("An android lib that provides a new size unit - sdp (scalable dp). This size unit scales with the screen size. It can help Android developers with supporting multiple screens.")
    }

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}

apollo {
    packageName.set("com.mbobiosio.rickandmorty")
}
