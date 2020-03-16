package com.danielpalmqvist.scala_mh

case class Human(
                  loc: Location,
                  infected: Boolean,
                  xMax: Int,
                  yMax: Int,
                  daysRemaining: Int
                )

object Human {
  def newHuman(xMax: Int, yMax: Int, infected: Boolean, diseaseLength: Int): Human =
    Human(Location.randomLocation(xMax, yMax), infected, xMax, yMax, diseaseLength)

  def step(human: Human, infectedLocations: Set[Location]): Human = {
    val loc = Location.step(human.loc, human.xMax, human.yMax)
    val onInfectedLocation = infectedLocations.contains(loc)
    Human(loc,
      (human.infected || onInfectedLocation) && (human.daysRemaining > 0),
      human.xMax,
      human.yMax,
      if (human.infected && human.daysRemaining > 0) human.daysRemaining - 1 else human.daysRemaining)
  }
}

