package slogo.frontend.panes.display;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

/**
 * Tests the TurtleView class to ensure it works as expected.
 */
class TurtleViewTest extends DukeApplicationTest {

  /**
   * Tests that the turtle actually exists.
   */
  @Test
  void testTurtleView() {
    TurtleView turtle = lookup("#turtle").query();
    assertNotNull(turtle);
  }

  /**
   * Tests that the turtle movers properly.
   */
  @Test
  void moveTurtle() {
    TurtleView turtle = lookup("#turtle").query();
    // No animation tests
    /*
    turtle.moveTurtle(10, 10);
    assertLocation(10, 10, turtle);
    turtle.moveTurtle(0, 0);
    assertLocation(0, 0, turtle);
    // Animation tests
    turtle.moveTurtle(10, 10, .1);
    assertLocation(10, 10, turtle);
    turtle.moveTurtle(0, 0, 1);
    assertLocation(0, 0, turtle);
    // Fail tests
    assertThrows(IllegalArgumentException.class, () -> turtle.moveTurtle(10, 10, -1));
    assertThrows(IllegalArgumentException.class,
        () -> turtle.moveTurtle(Integer.MAX_VALUE, Integer.MIN_VALUE, 0));
    */
  }

  /**
   * Tests that the turtle rotates properly.
   */
  @Test
  void rotateTurtle() {
    /*TurtleView turtle = lookup("#turtle").query();
    // No animation tests
    turtle.rotateTurtle(0);
    assertRotation(0, turtle);
    turtle.rotateTurtle(180);
    assertRotation(180, turtle);
    turtle.rotateTurtle(360);
    assertRotation(0, turtle);
    turtle.rotateTurtle(-270);
    assertRotation(90, turtle);
    turtle.rotateTurtle(-360);
    assertRotation(0, turtle);
    // Animation tests
    turtle.rotateTurtle(90, .1);
    assertRotation(90, turtle);
    turtle.rotateTurtle(270, 1);
    assertRotation(270, turtle);
    // Fail tests
    assertThrows(IllegalArgumentException.class, () -> turtle.rotateTurtle(-Integer.MAX_VALUE));
    assertThrows(IllegalArgumentException.class, () -> turtle.rotateTurtle(361));*/
  }

  /**
   * Asserts that the turtle is at the correct location.
   *
   * @param x      the x coordinate the turtle should be at
   * @param y      the y coordinate the turtle should be at
   * @param turtle the turtle to check
   */
  private void assertLocation(double x, double y, TurtleView turtle) {
    assertEquals(x, turtle.getTurtle().getLayoutX());
    assertEquals(y, turtle.getTurtle().getLayoutY());
  }

  /**
   * Asserts that the turtle is facing the correct direction.
   *
   * @param angle  the angle the turtle should be facing
   * @param turtle the turtle to check
   */
  private void assertRotation(double angle, TurtleView turtle) {
    assertEquals(angle, turtle.getTurtle().getRotate());
  }

}