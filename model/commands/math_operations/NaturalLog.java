package slogo.model.commands.math_operations;

import java.util.List;
import slogo.model.SingleTurtle;
import slogo.model.commands.Command;

public class NaturalLog extends Command {
  List<Object> myParameters;

  public NaturalLog(List<Object>parameters){
    myParameters = parameters;
  }

  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    List<Double> paramValues = evaluateParameters(myParameters, singleTurtle);
    return Math.log(paramValues.get(0));
  }
}