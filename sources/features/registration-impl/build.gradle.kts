plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.evgeny.goncharov.sample.multimodule.registration_impl"
    compileSdk = rootProject.ext["compileSdkVersionApp"] as Int
    defaultConfig {
        minSdk = rootProject.ext["minSdkVersionApp"] as Int
        targetSdk = rootProject.ext["targetSdkVersionApp"] as Int
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xexplicit-api=strict")
    }
}

dependencies {
    api(project(":sources:features:registration-api"))
}