package slogo.model.commands.math_operations;

import java.util.List;
import slogo.model.SingleTurtle;
import slogo.model.commands.Command;

public class Minus extends Command {
  List<Object> myParameters;

  public Minus(List<Object>parameters){
    myParameters = parameters;
  }

  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    List<Double> paramValues = evaluateParameters(myParameters, singleTurtle);
    return 0-paramValues.get(0);

  }
}
