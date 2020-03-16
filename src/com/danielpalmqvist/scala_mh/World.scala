package com.danielpalmqvist.scala_mh

import java.awt.{Color, Frame}

class World(numHumans: Int, xMax: Int, yMax: Int, numInfected: Int, diseaseLength: Int) {
  private var humans: Seq[Human] = (0 until numHumans).map(idx =>
    Human.newHuman(xMax, yMax, idx < numInfected, diseaseLength))
  private val frame = new Frame()
  private val pph = 1
  frame.setBackground(Color.BLACK)
  frame.setSize(xMax*pph, yMax*pph)
  frame.setVisible(true)
  frame.createBufferStrategy(2)
  val strategy = frame.getBufferStrategy
  private var stepNo: Int = 0
  def step = {
    val locations = infectedLocations
    humans = humans.map { case human =>
      Human.step(human, locations)
    }
    val graphics = strategy.getDrawGraphics
    graphics.setColor(Color.BLACK)
    graphics.fillRect(0, 0, xMax*pph, yMax*pph)
    new Pane(xMax, yMax, pph, humans).paint(graphics)
    graphics.dispose()
    strategy.show()
    Thread.sleep(5)
    stepNo += 1
  }

  private def infectedLocations: Set[Location] = {
    humans.flatMap(human => Some(human.infected).collect { case true => human.loc }).toSet
  }

  def infectedCount: Int = {
    humans.count(_.infected)
  }

  def immuneCount: Int = {
    humans.count(_.daysRemaining == 0)
  }
}
