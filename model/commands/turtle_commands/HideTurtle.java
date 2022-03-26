package slogo.model.commands.turtle_commands;

import java.util.List;
import slogo.model.SingleTurtle;
import slogo.model.Turtle;
import slogo.model.commands.Command;

public class HideTurtle extends Command {

  private List<Object> myParameters;

  public HideTurtle(List<Object> parameters){
    myParameters = parameters;

  }


  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    singleTurtle.setTurtleVisible(false);
    return Command.FALSE;
  }
}
