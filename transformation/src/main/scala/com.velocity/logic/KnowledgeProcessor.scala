package com.velocity.logic

//import com.velocity.KnowledgeConfig
import com.velocity.date.DatetimeProvider

class KnowledgeProcessor extends DatetimeProvider {

  def processKnowledgeInTime(): String = {
    val website = ""
    val dateTime = currentTimeAsString()
    s"$website - $dateTime"
  }
}

object KnowledgeProcessor {
  def apply(): KnowledgeProcessor = {
    new KnowledgeProcessor
  }
}