package com.velocity.connection

import com.typesafe.config.ConfigFactory

trait KnowledgeConfig {
  val config = ConfigFactory.load()

  def getKnowledgeWebsite: String = {
    config.getString("knowledge.website")
  }
}
