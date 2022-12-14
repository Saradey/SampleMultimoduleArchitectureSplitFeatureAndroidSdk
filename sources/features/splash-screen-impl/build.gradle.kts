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
    api(project(":sources:features:splash-screen-api"))

    implementation(project(":sources:engine:navigation"))
    implementation(project(":sources:engine:di-core"))
    implementation(project(":sources:core:base-core"))
    implementation(project(":sources:resources:theme"))

    implementation(project(":sources:features:bottom-menu-api"))

    implementation(coreLibs.cicerone)
    implementation(uiLibs.viewBindingPropertyDelegate)
    implementation(uiLibs.constraint.layout)
    implementation(androidLibs.fragment)
    implementation(androidLibs.fragment.ktx)
    implementation(coreLibs.dagger)

    kapt(coreLibs.daggerCompiler)
}