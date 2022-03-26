package slogo.model.commands.boolean_operations;

import java.util.List;
import org.apache.commons.math3.util.Precision;
import slogo.model.SingleTurtle;
import slogo.model.commands.Command;

public class Equal extends Command {
  List<Object> myParameters;

  public Equal(List<Object>parameters){
    myParameters = parameters;
  }

  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    List<Double> paramValues = evaluateParameters(myParameters, singleTurtle);
    boolean equal = Precision.equals(paramValues.get(0), paramValues.get(1), Command.EPSILON);
    return equal ? Command.TRUE : Command.FALSE;
  }
}