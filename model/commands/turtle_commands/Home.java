package slogo.model.commands.turtle_commands;

import java.util.List;
import slogo.model.SingleTurtle;
import slogo.model.commands.Command;

public class Home extends Command {

  public Home(List<Object> parameter){

  }

  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    double oldX = singleTurtle.getX();
    double oldY = singleTurtle.getY();
    singleTurtle.setPosition(0, 0, true);
    return calculateDistanceMoved(singleTurtle, oldX, oldY);
  }

  private double calculateDistanceMoved(SingleTurtle singleTurtle, double oldX,
      double oldY) {
    return Math.sqrt(Math.pow(singleTurtle.getX()-oldX, 2) + Math.pow(singleTurtle.getY()-oldY, 2));
  }
}
