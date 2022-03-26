package slogo.model.commands.turtle_queries;

import java.util.List;
import slogo.model.SingleTurtle;
import slogo.model.Turtle;
import slogo.model.commands.Command;

public class YCoordinate extends Command {

  public YCoordinate(List<Object> parameter){
  }

  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    return singleTurtle.getY();
  }
}