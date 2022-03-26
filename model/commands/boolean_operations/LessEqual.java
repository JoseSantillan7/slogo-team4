package slogo.model.commands.boolean_operations;

import java.util.List;
import slogo.model.SingleTurtle;
import slogo.model.commands.Command;
import org.apache.commons.math3.util.Precision;

public class LessEqual extends Command {
  List<Object> myParameters;

  public LessEqual(List<Object>parameters){
    myParameters = parameters;
  }

  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    List<Double> paramValues = evaluateParameters(myParameters, singleTurtle);
    boolean lessThan = paramValues.get(0) < paramValues.get(1);
    boolean equal = Precision.equals(paramValues.get(0), paramValues.get(1), Command.EPSILON);
    return lessThan || equal ? Command.TRUE : Command.FALSE;
  }
}