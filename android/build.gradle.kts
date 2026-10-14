plugins {
    id("com.android.application") version "9.3.3" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.3.20" apply false
    // KSP genera el código de Room (RD-03, persistencia local).
    id("com.google.devtools.ksp") version "2.3.12" apply false
}
