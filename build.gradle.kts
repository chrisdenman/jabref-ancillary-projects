plugins {
    id("application")
}

group = "org.jabref"
version = "100.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.seleniumhq.selenium:selenium-java:4.34.0")
}

application {
    mainClass.set("org.jabref.Main")
}