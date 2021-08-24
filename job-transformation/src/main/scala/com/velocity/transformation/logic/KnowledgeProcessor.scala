package com.velocity.transformation.logic

import com.velocity.connection.KnowledgeConfig
import com.velocity.transformation.date.DatetimeProvider

class KnowledgeProcessor extends KnowledgeConfig with DatetimeProvider {

  def processKnowledgeInTime(): String = {
    val website = getKnowledgeWebsite
    val dateTime = currentTimeAsString()
    s"$website - $dateTime"
  }
}

object KnowledgeProcessor {
  def apply(): KnowledgeProcessor = {
    new KnowledgeProcessor
  }
}