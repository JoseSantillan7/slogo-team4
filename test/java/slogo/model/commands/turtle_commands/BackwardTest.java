package slogo.model.commands.turtle_commands;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Turtle;
import slogo.model.commands.math_operations.Sum;

class BackwardTest {
  private Turtle myTurtle;
  private double tolerance;

  /*@BeforeEach
  void setUp() {
    myTurtle = new Turtle(1);
    tolerance = .0000001;
  }

  @Test
  void testReturnValue(){
    assertEquals(10.0, new Backward(myTurtle, List.of(10.0)).getReturnValue(
        allTurtles.get(turtleID)), tolerance);
    assertEquals(1000.0, new Sum(myTurtle, List.of(0.0, 1000.0)).getReturnValue(
        allTurtles.get(turtleID)), tolerance);
    assertEquals(5.1, new Sum(myTurtle, List.of(5.0, 0.1)).getReturnValue(allTurtles.get(turtleID)), tolerance);
    assertThrows(IndexOutOfBoundsException.class, () -> new Sum(myTurtle, List.of(5.0)).getReturnValue(
        allTurtles.get(turtleID)));
  }

  @Test
  void testDoubleAndCommandObjects(){
    assertEquals(8.0, new Sum(myTurtle, List.of(5.0, new Sum(myTurtle, List.of(2.0, 1.0)))).getReturnValue(
        allTurtles.get(turtleID)), tolerance);
    assertEquals(11.0, new Sum(myTurtle, List.of(new Sum(myTurtle, List.of(5.0, 4.0)), 2.0)).getReturnValue(
        allTurtles.get(turtleID)), tolerance);
    assertEquals(9.0, new Sum(
        myTurtle, List.of(new Sum(myTurtle, List.of(5.0, 1.0)), new Sum(myTurtle, List.of(2.0, 1.0)))).getReturnValue(
        allTurtles.get(turtleID)), tolerance);
    assertThrows(IndexOutOfBoundsException.class, () -> new Sum(
        myTurtle, List.of(new Sum(myTurtle, List.of(5.0, 1.0)))).getReturnValue(
        allTurtles.get(turtleID)));
  }*/
}