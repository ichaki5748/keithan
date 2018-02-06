package com.ichaki5748.keithan


fun main(args: Array<String>) {

}

data class Environment(val name: String,
                       val boxes: List<Box>)

data class Box(
        val domainName: String,
        val user: String,
        val processes: List<Process>,
        val jobs: List<Job>,
        val ip: String? = null,
        val name: String? = null
)

interface Process // Marker for now

interface Job

data class Application(val name: String, val artifactIdentity: ArtifactIdentity)

data class JavaProcess(
        val application: Application,
        val instance: String,
        val jvmArgs: JvmArgs,
        val cfg: Config) : Process

data class JvmArgs(val args: List<String>)


interface ArtifactIdentity

data class NexusArtifactIdentity(val artifactId: String, val groupId: String) : ArtifactIdentity


interface Config


