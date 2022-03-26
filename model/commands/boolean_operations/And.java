package slogo.model.commands.boolean_operations;

import java.util.List;
import org.apache.commons.math3.util.Precision;
import slogo.model.SingleTurtle;
import slogo.model.Turtle;
import slogo.model.commands.Command;

public class And extends Command {
  List<Object> myParameters;

  public And(List<Object>parameters){

    myParameters = parameters;
  }

  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    List<Double> paramValues = evaluateParameters(myParameters, singleTurtle);
    boolean and = !Precision.equals(paramValues.get(0), Command.FALSE, Command.EPSILON) && !Precision.equals(paramValues.get(1), Command.FALSE, Command.EPSILON);
    return and ? Command.TRUE : Command.FALSE;
  }


}