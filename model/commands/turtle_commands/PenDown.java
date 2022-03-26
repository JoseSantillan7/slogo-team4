package slogo.model.commands.turtle_commands;

import java.util.List;
import slogo.model.SingleTurtle;
import slogo.model.commands.Command;

public class PenDown extends Command {

  private List<Object> myParameters;

  public PenDown(List<Object> parameters){
    myParameters = parameters;

  }


  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    singleTurtle.setPenUp(false);
    return Command.TRUE;
  }
}
