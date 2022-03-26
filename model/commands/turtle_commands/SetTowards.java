package slogo.model.commands.turtle_commands;

import java.util.List;
import slogo.model.SingleTurtle;
import slogo.model.commands.Command;

public class SetTowards extends Command {

  private double myNewHead;
  private List<Object> myParameters;

  public SetTowards(List<Object> parameter){
    myParameters = parameter;
  }

  private void runCommand(SingleTurtle singleTurtle){
    myNewHead = evaluateParameters(myParameters, singleTurtle).get(0);
    singleTurtle.setHead(myNewHead);
  }
  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    double oldHead = singleTurtle.getHead();
    runCommand(singleTurtle);
    return myNewHead-oldHead;
  }
}
