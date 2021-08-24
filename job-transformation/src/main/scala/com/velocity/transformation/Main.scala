package com.velocity.transformation

import com.velocity.transformation.logic.KnowledgeProcessor

object Main extends App {

  val result = KnowledgeProcessor().processKnowledgeInTime()

  println("Attained knowledge from:" + result)
}
