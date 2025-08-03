plugins {
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

application {
    mainClass.set("Main")
}

javafx {
    version = "24.0.2"
    modules = listOf("javafx.controls")
}

group = "org.jabref"
version = "0.0.1"

repositories {
    mavenCentral()
}
