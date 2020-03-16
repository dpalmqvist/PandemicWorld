package com.danielpalmqvist.scala_mh

import scala.util.Random

case class Location(
                     x: Int,
                     y: Int
                   )

object Location {
  def randomLocation(xMax: Int, yMax: Int) = {
    Location(Random.nextInt(xMax), Random.nextInt(yMax))
  }

  def step(location: Location, xMax: Int, yMax: Int) = {
    val newX = (location.x + Random.nextInt(3)-1+xMax)%xMax//Math.max(Math.min(location.x + Random.nextInt(3) - 1, xMax), 0)
    val newY = (location.y + Random.nextInt(3)-1+yMax)%yMax//Math.max(Math.min(location.y + Random.nextInt(3) - 1, yMax), 0)
    Location(newX, newY)
  }
}