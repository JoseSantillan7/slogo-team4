package slogo.model.commands.math_operations;

import java.util.List;
import slogo.model.SingleTurtle;
import slogo.model.commands.Command;

public class Product extends Command {
  public static final int numOfParameters = 2;
  List<Object>myParameters;

  public Product(List<Object>parameters){
    myParameters = parameters;
  }

  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    List<Double> paramValues = evaluateParameters(myParameters, singleTurtle);
    return paramValues.get(0)*paramValues.get(1);
  }
}
