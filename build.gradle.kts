import org.gradle.api.tasks.wrapper.Wrapper.DistributionType
import org.jetbrains.kotlin.gradle.plugin.android.AndroidGradleWrapper.srcDir

plugins {
    application
    kotlin("jvm") version "1.2.21"
}

application {
    mainClassName = "com.ichaki5748.keithan.someproject.ConfigMainKT"
}

dependencies {
    compile(kotlin("stdlib"))
    compile(kotlin("script-runtime"))
    compile(kotlin("script-util"))
    compile(kotlin("compiler-embeddable"))
}

repositories {
    jcenter()
}

task<Wrapper>("wrapper") {
    gradleVersion = "4.5"
    distributionType = DistributionType.ALL
}

java.sourceSets["main"].java{
    srcDir("src/main/resources")
}

