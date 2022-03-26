package slogo.model.commands.math_operations;

import java.util.List;
import slogo.model.SingleTurtle;
import slogo.model.commands.Command;

public class Difference extends Command {
  List<Object> myParameters;

  public Difference(List<Object>parameters){
    myParameters = parameters;
  }

  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    List<Double> paramValues = evaluateParameters(myParameters, singleTurtle);
    return paramValues.get(0)-paramValues.get(1);

  }
}
