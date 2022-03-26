package slogo.model.commands.math_operations;

import java.util.List;
import slogo.model.SingleTurtle;
import slogo.model.commands.Command;

public class RandomRange extends Command {
  List<Object> myParameters;

  public RandomRange(List<Object>parameters){
    myParameters = parameters;
  }

  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    List<Double> paramValues = evaluateParameters(myParameters, singleTurtle);
    double range = paramValues.get(1)-paramValues.get(0);
    return Math.round(Math.random()*range + paramValues.get(0));
  }
}