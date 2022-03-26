package slogo.model.commands.turtle_commands;

import java.util.List;
import slogo.model.SingleTurtle;
import slogo.model.commands.Command;

public class Right extends Command {
  public static final int TOTAL_DEGREES = 360;
  private double degreesTurned;
  private List<Object>myParameters;


  public Right(List<Object> parameter){

    myParameters = parameter;

  }
  private void runCommand(SingleTurtle singleTurtle) {
    degreesTurned = evaluateParameters(myParameters, singleTurtle).stream().mapToDouble(i -> i).sum();
    singleTurtle.setHead( singleTurtle.getHead()- degreesTurned%TOTAL_DEGREES);
  }

  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    runCommand(singleTurtle);

    return degreesTurned;
  }
}
