package slogo.model.commands.boolean_operations;

import java.util.List;
import org.apache.commons.math3.util.Precision;
import slogo.model.SingleTurtle;
import slogo.model.commands.Command;

public class GreaterEqual extends Command {
  List<Object> myParameters;

  public GreaterEqual(List<Object>parameters){
    myParameters = parameters;
  }

  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    List<Double> paramValues = evaluateParameters(myParameters, singleTurtle);
    boolean greaterThan = paramValues.get(0) > paramValues.get(1);
    boolean equal = Precision.equals(paramValues.get(0), paramValues.get(1), Command.EPSILON);
    return greaterThan || equal ? Command.TRUE : Command.FALSE;
  }
}