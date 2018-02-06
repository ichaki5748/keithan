package com.ichaki5748.keithan


class EnvironmentBuilder(val name: String) {

    private val boxes: MutableList<Box> = mutableListOf()

    fun box(domainName: String,
            user: String,
            init: BoxBuilder.() -> Unit) {

        val boxBuilder = BoxBuilder(domainName, user)
        boxBuilder.init()

        boxes.add(boxBuilder.build())
    }

    fun build(): Environment = Environment(name, boxes.toList())
}

class BoxBuilder(private val domainName: String,
                 private val user: String) {

    private val processes: MutableList<Process> = mutableListOf()
    private val jobs: MutableList<Job> = mutableListOf()

    fun process(process: Process) {
        processes.add(process)
    }

    fun job(job: Job) {
        jobs.add(job)
    }

    fun build(): Box {
        return Box(domainName, user, processes.toList(), jobs.toList())
    }


}


fun env(name: String, environmentBuilderInit: EnvironmentBuilder.() -> Unit): Environment {

    val envBuilder = EnvironmentBuilder(name)
    envBuilder.environmentBuilderInit()

    return envBuilder.build()
}