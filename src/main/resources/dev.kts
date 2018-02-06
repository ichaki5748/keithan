import com.ichaki5748.keithan.*
import com.ichaki5748.keithan.someproject.*

val baseInputJmsConfig: JmsConfig = JmsConfig("some-name", mapOf())

env("dev") {

    box("domain.a", "user-a") {

        process(JavaProcess(
                application = dataProducer,
                instance = "alpha",
                jvmArgs = StandardJvmArgs.smallJvm,
                cfg = DataProducerConfig(baseInputJmsConfig.copy(subscriberName = "overriden"), outputJmsConfig = JmsConfig("", mapOf())))
        )

        process(JavaProcess(
                application = dataProducer,
                instance = "beta",
                jvmArgs = StandardJvmArgs.smallJvm,
                cfg = DataProducerConfig(baseInputJmsConfig.copy(subscriberName = "overriden"), outputJmsConfig = JmsConfig("", mapOf())))
        )

        process(JavaProcess(
                application = dataProducer,
                instance = "gamma",
                jvmArgs = StandardJvmArgs.smallJvm,
                cfg = DataProducerConfig(baseInputJmsConfig.copy(subscriberName = "overriden"), outputJmsConfig = JmsConfig("", mapOf())))
        )

        process(JavaProcess(
                application = dqmProducer,
                instance = "alpha",
                jvmArgs = StandardJvmArgs.xlarge,
                cfg = DataProducerConfig(baseInputJmsConfig.copy(subscriberName = "overriden"), outputJmsConfig = JmsConfig("", mapOf()))
        ))

    }

}
