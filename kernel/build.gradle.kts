plugins {
    kotlin("kapt")
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hiltLibrary)
    alias(libs.plugins.kotlinParcelize)
}

android {
    namespace = "corp.hell.kernel"
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
            buildConfigField("String", "SERVER_NAME", "\"Development\"")
            buildConfigField("String", "SERVER_URL", "\"https://dev-babble-api.primathontech.co.in\"")
            buildConfigField("String", "CHAT_SOCKET_URL", "\"https://dev-chat.github.com\"")
            buildConfigField("String", "VOIP_SOCKET_URL", "\"https://dev-call.github.com\"")
        }
        create("qa") {
            dimension = "default"
            buildConfigField("String", "SERVER_NAME", "\"Staging\"")
            buildConfigField("String", "SERVER_URL", "\"https://qa-api.github.com\"")
            buildConfigField("String", "CHAT_SOCKET_URL", "\"https://qa-chat.github.com\"")
            buildConfigField("String", "VOIP_SOCKET_URL", "\"https://qa-call.github.com\"")
        }
        create("prod") {
            dimension = "default"
            buildConfigField("String", "SERVER_NAME", "\"Production\"")
            buildConfigField("String", "SERVER_URL", "\"https://prod-api.github.com\"")
            buildConfigField("String", "CHAT_SOCKET_URL", "\"https://prod-chat.github.com\"")
            buildConfigField("String", "VOIP_SOCKET_URL", "\"https://prod-call.github.com\"")
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
    implementation(libs.datastore)
    implementation(libs.datastore.core)

    //networking retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.chucker)

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

    //firebase
    implementation(libs.firebase.messaging.ktx)

    //size
    implementation(libs.size.sdp)
    implementation(libs.size.ssp)

    //Image loading library Coil and Glide
    implementation(libs.coil)
    implementation(libs.coil.svg)
    implementation(libs.coil.gif)
    implementation(libs.glide)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}