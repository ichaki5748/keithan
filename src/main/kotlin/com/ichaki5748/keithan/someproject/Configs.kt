package com.ichaki5748.keithan.someproject

import com.ichaki5748.keithan.Config

data class DataProducerConfig(val inputJmsConfig: JmsConfig, val outputJmsConfig: JmsConfig) : Config

data class JmsConfig(val subscriberName: String,
                     val ic: Map<String, String>)

