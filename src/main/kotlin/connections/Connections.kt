package connections

interface ServiceIdentity

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

