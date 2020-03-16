package com.danielpalmqvist.scala_mh
import java.awt.image.BufferedImage
import java.awt.{BasicStroke, Color, Font, Frame, Graphics2D}
import java.awt.geom._


object Pandemic extends App {
  private final val NUM_HUMANS = 20000
  private final val MAX_X = 640
  private final val MAX_Y = 480
  private final val diseaseLength = 60
  val frame = new Frame()
  var numWorlds = 1000
  while (numWorlds>0) {
    numWorlds -= 1
    val world = new World(NUM_HUMANS, MAX_X, MAX_Y, 1, diseaseLength)
    var time = 0
    while (world.infectedCount > 0) {
      world.step
      time += 1
    }
    println(s"$numWorlds, $time, ${world.infectedCount}, ${world.immuneCount}, ${NUM_HUMANS}")
  }
}
