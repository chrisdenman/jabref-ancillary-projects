plugins {
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

application {
    mainClass.set("org.jabref.Main")
}

javafx {
    version = "24.0.2"
    modules = listOf("javafx.controls", "javafx.web")
}

group = "org.jabref"
version = "0.0.1"

repositories {
    mavenCentral()
}
