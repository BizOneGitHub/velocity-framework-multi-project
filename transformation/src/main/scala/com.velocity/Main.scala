package com.velocity

import com.velocity.logic.KnowledgeProcessor

object Main extends App {

  val result = KnowledgeProcessor().processKnowledgeInTime()

  println("Attained knowledge from:" + result)
}
