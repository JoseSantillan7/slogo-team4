package slogo.model.commands.boolean_operations;

import java.util.List;
import slogo.model.SingleTurtle;
import slogo.model.commands.Command;

public class LessThan extends Command {
  List<Object> myParameters;

  public LessThan(List<Object>parameters){
    myParameters = parameters;
  }

  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    List<Double> paramValues = evaluateParameters(myParameters, singleTurtle);
    boolean lessThan = paramValues.get(0) < paramValues.get(1);
    return lessThan ? Command.TRUE : Command.FALSE;
  }
}
