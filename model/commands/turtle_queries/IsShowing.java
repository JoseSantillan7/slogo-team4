package slogo.model.commands.turtle_queries;

import java.util.List;
import slogo.model.SingleTurtle;
import slogo.model.Turtle;
import slogo.model.commands.Command;

public class IsShowing extends Command {


  public IsShowing(Turtle turtle, List<Object> parameters){
  }

  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    return singleTurtle.getIsTurtleVisible() ? Command.TRUE : Command.FALSE;
  }
}
