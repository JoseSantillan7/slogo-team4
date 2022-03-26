package slogo.model.commands.turtle_commands;

import java.util.List;
import slogo.model.SingleTurtle;
import slogo.model.commands.Command;

public class SetPosition extends Command {

  private List<Object> myParameters;

  public SetPosition( List<Object> parameter){
    myParameters = parameter;

  }

  private void runCommand(SingleTurtle singleTurtle){
    double x = evaluateParameters(myParameters, singleTurtle).get(0);
    double y = evaluateParameters(myParameters, singleTurtle).get(1);
    singleTurtle.setPosition(x, y, true);
  }
  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    double oldX = singleTurtle.getX();
    double oldY = singleTurtle.getY();
    runCommand(singleTurtle);
    return calculateDistanceMoved(singleTurtle,oldX, oldY);
  }

  private double calculateDistanceMoved(SingleTurtle singleTurtle, double oldX,
      double oldY) {
    return Math.sqrt(Math.pow(singleTurtle.getX()-oldX, 2) + Math.pow(singleTurtle.getY()-oldY, 2));
  }
}
