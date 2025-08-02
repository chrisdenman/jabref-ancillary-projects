plugins {
    id("application")
}

group = "org.jabref"
version = "0.0.1"

repositories {
    mavenCentral()
}

application {
    mainClass.set("Main")
}