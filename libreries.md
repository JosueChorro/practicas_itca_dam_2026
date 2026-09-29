# Guía de Configuración: Navigation Compose y Room en Android

Este documento es una referencia rápida para configurar las dependencias de **Navigation Compose**, base de datos **Room**, conectividad de recursos HTTP **Retrofit** y **Corrutinas**.

Sigue los pasos a continuación para implementar estas herramientas en tu proyecto.

---

## Paso 1: Configurar el Catálogo de Versiones

Abre el archivo `libs.versions.toml` y agrega las siguientes definiciones. Este archivo centraliza todas las versiones y librerías del proyecto para mantener el orden.

```toml
[versions]
navigationCompose = "2.9.8"
room = "2.8.0"
coroutines = "1.9.0"
retrofit = "3.0.0"
kotlinSerialization = "1.9.0"
kotlinSerializationConverter = "1.0.0"

[libraries]
# Navigation Compose
androidx-navigation-compose = { group = "androidx.navigation", name = "navigation-compose", version.ref = "navigationCompose" }

# Room (Base de Datos)
androidx-room-runtime = { group = "androidx.room", name = "room-runtime", version.ref = "room" }
androidx-room-ktx = { group = "androidx.room", name = "room-ktx", version.ref = "room" }
androidx-room-compiler = { group = "androidx.room", name = "room-compiler", version.ref = "room" }

# Corrutinas e Iconos
kotlinx-coroutines-android = { group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-android", version.ref = "coroutines" }
compose-material-icons = { group = "androidx.compose.material", name = "material-icons-core" }

# Retrofit
retrofit = { group = "com.squareup.retrofit2", name = "retrofit", version.ref = "retrofit" }

#Serialization
retrofitConvertGson = { module = "com.squareup.retrofit2:converter-gson", version.ref = "retrofit" }

#Serialization
kotlinx-serialization-json = { group = "org.jetbrains.kotlinx", name = "kotlinx-serialization-json", version.ref = "kotlinSerialization" }

#Serialization
retrofit-kotlinx-serialization = { group = "com.jakewharton.retrofit", name = "retrofit2-kotlinx-serialization-converter", version.ref = "kotlinSerializationConverter" }

[plugins]
# KSP (Kotlin Symbol Processing) necesario para compilar Room
google-devtools-ksp = { id = "com.google.devtools.ksp", version = "2.0.21-1.0.26" }
kotlin-serialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "kotlin" }
```

---

## Paso 2: Configurar Gradle a nivel de Proyecto

Abre el archivo `build.gradle.kts` **(Project)**. Aquí debemos declarar el plugin de KSP, pero sin aplicarlo directamente a todo el proyecto.

```kotlin
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.google.devtools.ksp) apply false
    alias (libs.plugins.kotlin.serialization) apply false
}
```

---

## Paso 3: Configurar Gradle a nivel de Módulo

Abre el archivo `build.gradle.kts` **(Module: app)**. Aquí aplicaremos el plugin de KSP y agregaremos las dependencias que declaramos en el Paso 1.

### 3.1 Aplicar el Plugin

En la parte superior del archivo, dentro del bloque `plugins`, agrega KSP:

```kotlin
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.kotlin.serialization)
}
```

### 3.2 Agregar las Dependencias

Desplázate hasta el bloque `dependencies` y agrega las implementaciones:

```kotlin
dependencies {
    implementation(libs.androidx.navigation.compose)

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    
    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.kotlinx.serialization)

    // Retrofit
    implementation(libs.retrofit)

    // Gson
    implementation(libs.retrofitConverterGson)
    implementation(libs.kotlinx.coroutines.android)
}
```