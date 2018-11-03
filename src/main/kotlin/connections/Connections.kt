package connections

interface ServiceIdentity

data class StringServiceIdentity(val identity: String) : ServiceIdentity

interface ServiceDefinition<IDENTITY : ServiceIdentity, SERVICE_CONFIG> {
  val serviceIdentity: IDENTITY
  val serviceConfig: SERVICE_CONFIG
}

interface ConnectionFactory<PARAMS, CONNECTION_PROPS> {
  fun makeConnectionProps(params: PARAMS): CONNECTION_PROPS
}

interface ConnectionTo<PARAMS,
    CONNECTION_PROPS,
    SERVICE_IDENTITY : ServiceIdentity,
    SERVICE_CONFIG> {

  val params: PARAMS
  val to: ServiceDefinition<SERVICE_IDENTITY, SERVICE_CONFIG>
  val connectionFactoryExtractor: (SERVICE_CONFIG) -> ConnectionFactory<PARAMS, CONNECTION_PROPS>
}

data class ConnectionToHolder<PARAMS,
    CONNECTION_PROPS,
    SERVICE_IDENTITY : ServiceIdentity,
    SERVICE_CONFIG>(override val params: PARAMS,
                    override val to: ServiceDefinition<SERVICE_IDENTITY, SERVICE_CONFIG>,
                    override val connectionFactoryExtractor: (SERVICE_CONFIG) -> ConnectionFactory<PARAMS, CONNECTION_PROPS>)
  : ConnectionTo<PARAMS,
    CONNECTION_PROPS,
    SERVICE_IDENTITY,
    SERVICE_CONFIG>

// *****************************************************

interface JCenter {
  val url: String
  val connectionFactory: ConnectionFactory<String, String>
}

interface GradleConfig {
  val jcenterConnection: ConnectionToHolder<String, String, StringServiceIdentity, JCenter>
}

data class GradleConfigHolder(
    override val jcenterConnection: ConnectionToHolder<String, String, StringServiceIdentity, JCenter>
) : GradleConfig

data class JCenterHolder(override val url: String,
                         override val connectionFactory: ConnectionFactory<String, String>) : JCenter

data class JCenterServiceDefinition(override val serviceIdentity: StringServiceIdentity,
                                    override val serviceConfig: JCenter) : ServiceDefinition<StringServiceIdentity, JCenter>

class UrlConnectionFactory : ConnectionFactory<String, String> {
  override fun makeConnectionProps(params: String): String {
    return params
  }
}

val globalJcenter = JCenterServiceDefinition(
    StringServiceIdentity("global"),
    serviceConfig = JCenterHolder(
        "https://bintray.com/bintray/jcenter",
        UrlConnectionFactory()
    )
)

val localGradleClient = GradleConfigHolder(
    ConnectionToHolder("dummy",
        globalJcenter
    ) { it.connectionFactory }
)
