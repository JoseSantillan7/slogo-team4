package slogo.model.commands.math_operations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Turtle;

class ProductTest {
  private Turtle myTurtle;
  private double tolerance;

  /*@BeforeEach
  void setUp() {
    myTurtle = new Turtle(1);
    tolerance = .0000001;
  }

  @Test
  void testTwoDoubleObjects(){
    assertEquals(5.0*2.0, new Product(myTurtle, List.of(5.0, 2.0)).getReturnValue(
        allTurtles.get(turtleID)), tolerance);
    assertEquals(0.0*1000.0, new Product(myTurtle, List.of(0.0, 1000.0)).getReturnValue(
        allTurtles.get(turtleID)), tolerance);
    assertEquals(5.0*0.1, new Product(myTurtle, List.of(5.0, 0.1)).getReturnValue(
        allTurtles.get(turtleID)), tolerance);
    assertThrows(IndexOutOfBoundsException.class, () -> new Product(myTurtle, List.of(5.0)).getReturnValue(
        allTurtles.get(turtleID)));
  }

  @Test
  void testDoubleAndCommandObjects(){
    assertEquals(10.0, new Product(myTurtle, List.of(5.0, new Product(myTurtle, List.of(2.0, 1.0)))).getReturnValue(
        allTurtles.get(turtleID)), tolerance);
    assertEquals(10.0, new Product(myTurtle, List.of(new Product(myTurtle, List.of(5.0, 1.0)), 2.0)).getReturnValue(
        allTurtles.get(turtleID)), tolerance);
    assertEquals(10.0, new Product(myTurtle, List.of(new Product(myTurtle, List.of(5.0, 1.0)), new Product(
        myTurtle, List.of(2.0, 1.0)))).getReturnValue(allTurtles.get(turtleID)), tolerance);
    assertThrows(IndexOutOfBoundsException.class, () -> new Product(
        myTurtle, List.of(new Product(myTurtle, List.of(5.0, 1.0)))).getReturnValue(
        allTurtles.get(turtleID)));
  }*/
}