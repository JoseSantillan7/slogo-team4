package slogo.model.commands.math_operations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Turtle;
import slogo.model.commands.turtle_queries.Heading;

class DifferenceTest {
  private Turtle myTurtle;
  private double tolerance;

  /*@BeforeEach
  void setUp() {
    myTurtle = new Turtle(1);
    tolerance = .0000001;
  }*/
  /*@Test
  void testTwoDoubleObjects(){
    assertEquals(3.0, new Difference(myTurtle, List.of(5.0, 2.0)).getReturnValue(
        allTurtles.get(turtleID)), tolerance);
    assertEquals(-1000.0, new Difference(myTurtle, List.of(0.0, 1000.0)).getReturnValue(
        allTurtles.get(turtleID)), tolerance);
    assertEquals(-7, new Difference(myTurtle, List.of(-5.0, 2.0)).getReturnValue(
        allTurtles.get(turtleID)), tolerance);
    assertEquals(-3, new Difference(myTurtle, List.of(-5.0, -2.0)).getReturnValue(
        allTurtles.get(turtleID)), tolerance);
    assertThrows(IndexOutOfBoundsException.class, () -> new Difference(myTurtle, List.of(5.0)).getReturnValue(
        allTurtles.get(turtleID)));
  }*/

  /*@Test
  void testDoubleAndCommandObjects(){
    assertEquals(4, new Difference(myTurtle, List.of(5.0, new Difference(myTurtle, List.of(2.0, 1.0)))).getReturnValue(
        allTurtles.get(turtleID)), tolerance);
    assertEquals(2, new Difference(
        myTurtle, List.of(new Difference(myTurtle, List.of(5.0, 1.0)), 2.0)).getReturnValue(
        allTurtles.get(turtleID)), tolerance);
    assertEquals(3, new Difference(
        myTurtle, List.of(new Difference(myTurtle, List.of(5.0, 1.0)), new Difference(
        myTurtle, List.of(2.0, 1.0)))).getReturnValue(allTurtles.get(turtleID)), tolerance);
    assertThrows(IndexOutOfBoundsException.class, () -> new Difference(
        myTurtle, List.of(new Heading(myTurtle, new ArrayList<Object>()))).getReturnValue(
        allTurtles.get(turtleID)));
  }*/

}