package com.velocity.logic

import com.velocity.KnowledgeConfig
import com.velocity.date.DatetimeProvider

class KnowledgeProcessor extends KnowledgeConfig with DatetimeProvider {

  def processKnowledgeInTime(): String = {
    val website = getKnowledgeWebsite
    val dateTime = currentTimeAsString()
    s"$website - $dateTime"
  }
}

object KnowledgeProcessor {
  def apply(): KnowledgeProcessor =
    new KnowledgeProcessor
}
