plugins {
    kotlin("kapt")
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hiltLibrary)
    alias(libs.plugins.kotlinParcelize)
}
val appVersionCode = libs.versions.versionMajor.get().toInt() + libs.versions.versionMinor.get()
    .toInt() + libs.versions.versionBuild.get().toInt() + libs.versions.versionPatch.get().toInt()
val appVersionName = "${libs.versions.versionMajor.get()}.${libs.versions.versionMinor.get()}" +
        ".${libs.versions.versionBuild.get()}.${libs.versions.versionPatch.get()}"

android {
    namespace = "corp.hell.archer"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "corp.hell.archer"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = appVersionCode
        versionName = appVersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
        }
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += "default"
    productFlavors {
        create("dev") { dimension = "default" }
        create("qa") { dimension = "default" }
        create("prod") { dimension = "default" }
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file("../imp/dev_archer_tollFreeBond.jks")
            storePassword = "1800007007"
            keyPassword = "1800007007"
            keyAlias = "babble"
        }
        create("release") {
            storeFile = file("../imp/release_archer_tollFreeBond.jks")
            storePassword = "1800007007"
            keyPassword = "1800007007"
            keyAlias = "babble"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    //auto view binding for gradle 4.0 +
    buildFeatures {
        buildConfig = true
        viewBinding = true
        this.dataBinding = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    applicationVariants.all {
        val variant = this
        variant.outputs
            .filterIsInstance<com.android.build.gradle.internal.api.BaseVariantOutputImpl>()
            .forEach {
                it.outputFileName =
                    "${variant.name}-v${appVersionName}.apk"
            }
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
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.material)

    //networking
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

    //paging
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
    implementation(libs.coil) //coil
    implementation(libs.coil.svg) //coil svg
    implementation(libs.coil.gif) //coil gif
    implementation(libs.glide)//glide

    //firebase
    implementation(libs.firebase.common.ktx)
    implementation(libs.firebase.messaging.ktx)
    implementation(libs.firebase.crashlytics.ktx)

    //project
    implementation(project(":kernel"))
    implementation(project(":main"))

}
// Allow references to generated code
kapt {
    correctErrorTypes = true
}