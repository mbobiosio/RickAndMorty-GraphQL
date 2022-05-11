
object AndroidBuild {
    const val COMPILE_SDK = 32
    const val APPLICATION_ID = "com.mbobiosio.rickandmorty"
    const val MIN_ANDROID_SDK = 23
    const val TARGET_ANDROID_SDK = 32
    const val VERSION_CODE = 1
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    val versionName by lazy {
        val x = VERSION_CODE / 10_000
        val y = (VERSION_CODE % 10_000) / 100
        val z = VERSION_CODE % 100

        // val date = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
        "$x.$y.$z"
        //
    }
}
object AndroidX {
    const val KOTLIN_VERSION = "1.5.31"
}

object Versions {
    const val CORE_KTX = "1.7.0"
    const val APP_COMPAT = "1.4.1"
    const val MATERIAL = "1.7.0-alpha01"
    const val CONSTRAINT_LAYOUT = "2.1.3"
    const val NAVIGATION = "2.4.2"
    const val RETROFIT = "2.9.0"
    const val ROOM = "2.4.2"
    const val OKHTTP = "4.9.3"
    const val COIL = "1.4.0"
    const val COROUTINES = "1.6.1"
    const val DAGGER_HILT = "2.41"
    const val PAGING3 = "3.1.1"
    const val LOGGING = "4.9.0"
    const val TIMBER = "5.0.1"
    const val INTUIT = "1.0.6"
}
