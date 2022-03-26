package slogo.model.commands.math_operations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.SingleTurtle;
import slogo.model.Turtle;
import slogo.model.commands.turtle_queries.Heading;

class QuotientTest {
  private SingleTurtle myTurtle;
  private double tolerance;
  @BeforeEach
  void setUp() {
    myTurtle = new SingleTurtle(1, null, null,null,null,null);
    tolerance = .0000001;
  }
  @Test
  void testTwoDoubleObjects(){
    assertEquals(5.0/2.0, new Quotient(List.of(5.0, 2.0)).getReturnValue(
       myTurtle), tolerance);
    assertEquals(0.0/1000.0, new Quotient(List.of(0.0, 1000.0)).getReturnValue(
        myTurtle), tolerance);
    assertEquals(-2.5, new Quotient(List.of(-5.0, 2.0)).getReturnValue(
        myTurtle), tolerance);
    assertEquals(2.5, new Quotient(List.of(-5.0, -2.0)).getReturnValue(
        myTurtle), tolerance);
    assertEquals(Double.parseDouble("Infinity"), new Quotient(List.of(5.0, 0.0)).getReturnValue(
        myTurtle), tolerance);
    assertThrows(IndexOutOfBoundsException.class, () -> new Quotient(List.of(5.0)).getReturnValue(
        myTurtle));
  }

  @Test
  void testDoubleAndCommandObjects(){
    assertEquals(5.0/2.0, new Quotient(
        List.of(5.0, new Quotient(List.of(2.0, 1.0)))).getReturnValue(
        myTurtle), tolerance);
    assertEquals(5.0/2.0, new Quotient(
         List.of(new Quotient(List.of(5.0, 1.0)), 2.0)).getReturnValue(
        myTurtle), tolerance);
    assertEquals(5.0/2.0, new Quotient(
        List.of(new Quotient(List.of(5.0, 1.0)), new Quotient(
        List.of(2.0, 1.0)))).getReturnValue(myTurtle), tolerance);
    assertEquals(Double.parseDouble("Infinity"), new Quotient(
        List.of(5.0, new Heading(new ArrayList<Object>()))).getReturnValue(
        myTurtle), tolerance);
    assertThrows(IndexOutOfBoundsException.class, () -> new Quotient(
        List.of(new Heading(new ArrayList<Object>()))).getReturnValue(
        myTurtle));
  }



}