package com.danielpalmqvist.scala_mh

import java.awt.{Color, Component, Graphics, Graphics2D, Image}

class Pane(xMax: Int, yMax: Int, pph: Int, humans: Seq[Human]) extends Component {
  def getMaxX: Int = pph * xMax
  def getMaxY: Int = pph * yMax

  override def paint(g: Graphics) = {
    val g2d = g.asInstanceOf[Graphics2D]
    // clear background
    //g2d.setColor(Color.BLACK)
    //g2d.fillRect(0, 0, xMax*pph, yMax*pph)

    humans
      .map(human => (human.loc, (human.infected, human.daysRemaining)))
      .foldLeft(Map.empty[Location, Int]) {
        case (acc, (loc, (false, remaining))) if remaining > 0 =>
          if (acc.contains(loc)) acc.updated(loc, Math.max(0, acc(loc)))
          else acc.updated(loc, 0)
        case (acc, (loc, (true, remaining))) if remaining > 0 =>
          if (acc.contains(loc)) acc.updated(loc, Math.max(1, acc(loc)))
          else acc.updated(loc, 1)
        case (acc, (loc, (_, 0))) =>
          acc.updated(loc, 2)
      }
      .filterNot(_._2 == 0)
      .foreach {
        case (loc, state) =>
          g2d.setColor {
            state.toInt match {
              case 0 => Color.BLACK
              case 1 => Color.RED
              case 2 => Color.GREEN
            }
          }
          g2d.fillRect(loc.x * pph, loc.y * pph, pph, pph)
      }
  }
}
