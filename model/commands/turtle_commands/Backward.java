package slogo.model.commands.turtle_commands;

import java.util.List;
import slogo.model.SingleTurtle;
import slogo.model.Turtle;
import slogo.model.commands.Command;

public class Backward extends Command {
  public static final int numOfParameters = 1;
  private double myDistance;
  private List<Object>myParameters;
  private Turtle myTurtle;

  public Backward(List<Object>parameter){
    myParameters = parameter;
  }

  public void runCommand(SingleTurtle singleTurtle){
    myDistance = evaluateParameters(myParameters, singleTurtle).stream().mapToDouble(i -> i).sum();
    double newX = singleTurtle.getX() - Math.cos(Math.toRadians(singleTurtle.getHead()))*myDistance;
    double newY = singleTurtle.getY() - Math.sin(Math.toRadians(singleTurtle.getHead()))*myDistance;
    singleTurtle.setPosition(newX, newY, true);
  }


  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    runCommand(singleTurtle);
    return myDistance;
  }


}
