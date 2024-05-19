plugins {
    kotlin("kapt")
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hiltLibrary)
    alias(libs.plugins.kotlinParcelize)
}
apply(from = "${rootProject.projectDir}/common.gradle.kts")

android {
    namespace = "co.si.main"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    flavorDimensions += "default"
    productFlavors {
        create("dev") {
            dimension = "default"
        }
        create("qa") {
            dimension = "default"
        }
        create("prod") {
            dimension = "default"
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    //auto view binding for gradle 4.0 +
    buildFeatures {
        buildConfig = true
        viewBinding = true
        this.dataBinding = true
    }

}

dependencies {
    //test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //core
    implementation(libs.fragment.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    //networking retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
//    implementation(libs.chucker)
//    implementation(libs.chucker.no.op)

    //common
    implementation(libs.timber.logging)
    implementation(libs.leakCanary)

    //livedata & viewmodel
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.common)

    //paging3
    implementation(libs.paging3)

    //navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    //hilt
    implementation(libs.hilt.dagger)
    kapt(libs.hilt.dagger.compiler)

    //size
    implementation(libs.size.sdp)
    implementation(libs.size.ssp)

    //Image loading library Coil and Glide
    implementation(libs.coil)
    implementation(libs.coil.svg)
    implementation(libs.coil.gif)
    implementation(libs.glide)

    //firebase
    implementation(libs.firebase.messaging.ktx)

    //project
    implementation(project(":kernel"))
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}