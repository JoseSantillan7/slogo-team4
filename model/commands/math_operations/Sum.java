package slogo.model.commands.math_operations;

import java.util.List;
import slogo.model.SingleTurtle;
import slogo.model.commands.Command;

public class Sum extends Command {
  private List<Object> myParameters;

  public Sum(List<Object>parameters){
    myParameters = parameters;

  }

  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    List<Double> paramValues = evaluateParameters(myParameters, singleTurtle);
    return paramValues.stream().mapToDouble(i -> i).sum();
  }
}