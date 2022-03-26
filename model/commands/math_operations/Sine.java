package slogo.model.commands.math_operations;

import java.util.List;
import slogo.model.SingleTurtle;
import slogo.model.commands.Command;

public class Sine extends Command {
  List<Object> myParameters;

  public Sine(List<Object>parameters){
    myParameters = parameters;
  }

  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    List<Double> paramValues = evaluateParameters(myParameters, singleTurtle);
    return Math.sin(Math.toRadians(paramValues.get(0)));
  }
}
