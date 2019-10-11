plugins {
    id("org.jetbrains.kotlin.jvm")
    `java-library`
}

repositories {
    jcenter()
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
}
