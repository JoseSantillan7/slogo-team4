package slogo.frontend;

import slogo.frontend.panes.display.TurtleView;

/**
 * Interface to allow access to the turtle view of the turtle.
 *
 * @author Ritivk Janamsetty
 */
public interface Turtleable {

  /**
   * Get and sets the turtle view of the turtle.
   *
   * @param id     the id of the turtle
   * @param turtle the turtle view
   */
  void accessTurtle(int id, TurtleView turtle);

}
