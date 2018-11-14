import org.gradle.api.tasks.wrapper.Wrapper.DistributionType

repositories {
  jcenter()
}

plugins {
  application
  kotlin("jvm") version "1.3.10"
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

tasks.withType<Wrapper> {
  gradleVersion = "4.10.2"
  distributionType = DistributionType.ALL
}

