package com.ichaki5748.keithan.someproject

import com.ichaki5748.keithan.Application
import com.ichaki5748.keithan.JvmArgs
import com.ichaki5748.keithan.NexusArtifactIdentity

private val artifactGroupId = "com.ichaki5748"


val dataProducer: Application = Application("data-producer", NexusArtifactIdentity("data-producer", artifactGroupId))

val dqmProducer: Application = Application("dqm-producer", NexusArtifactIdentity("dqm-producer", artifactGroupId))

// etc

object StandardJvmArgs {

    enum class HeapMeasures {
        m,
        g
    }

    val smallJvm = generateJvmArgs(256)
    val mediumJvm = generateJvmArgs(512)
    val large = generateJvmArgs(1024)
    val xlarge = generateJvmArgs(1512)
    val xxlarge = generateJvmArgs(2048)

    private fun generateJvmArgs(xms: Int, xmx: Int = xms, measure: HeapMeasures = HeapMeasures.m): JvmArgs {
        return JvmArgs(listOf("-Xms$xms$measure -Xmx$xmx$measure"))
    }
}


