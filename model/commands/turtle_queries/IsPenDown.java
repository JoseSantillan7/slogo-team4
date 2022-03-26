package slogo.model.commands.turtle_queries;

import java.util.List;
import slogo.model.SingleTurtle;
import slogo.model.Turtle;
import slogo.model.commands.Command;

public class IsPenDown extends Command {


  public IsPenDown(List<Object> parameters){

  }

  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    return singleTurtle.getIsPenUp() ? Command.FALSE : Command.TRUE;
  }
}
