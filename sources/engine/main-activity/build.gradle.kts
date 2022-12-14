plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":sources:engine:di-core"))
    implementation(project(":sources:engine:navigation"))
    implementation(project(":sources:features:splash-screen-api"))

    implementation(uiLibs.app.compat)
    implementation(uiLibs.material)
    implementation(coreLibs.dagger)
    implementation(coreLibs.cicerone)
    kapt(coreLibs.daggerCompiler)
}