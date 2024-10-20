// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // android plugins
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    // kotlin plugins
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    // annotation processors
    alias(libs.plugins.ksp) apply false
    // dependency injection
    alias(libs.plugins.hilt) apply false
    // compose
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlin.android) apply false
}