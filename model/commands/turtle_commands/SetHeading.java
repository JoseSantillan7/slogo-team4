package slogo.model.commands.turtle_commands;

import java.util.List;
import slogo.model.SingleTurtle;
import slogo.model.Turtle;
import slogo.model.commands.Command;

public class SetHeading extends Command {

  private double myNewHead;
  private List<Object>myParameters;

  public SetHeading( List<Object> parameter){
    myParameters = parameter;

  }

  private void runCommand(SingleTurtle singleTurtle){
    double x = evaluateParameters(myParameters, singleTurtle).get(0);
    double y = evaluateParameters(myParameters, singleTurtle).get(1);
    myNewHead = Math.tan(y/x);
    singleTurtle.setHead(myNewHead);
  }
  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    double oldHead = singleTurtle.getHead();
    runCommand(singleTurtle);
    return myNewHead-oldHead;
  }
}