package slogo.model.commands.control;

import java.util.ArrayList;
import java.util.List;
import slogo.model.SingleTurtle;
import slogo.model.commands.Command;

public class Repeat extends Command {
  private List<List<Object>> myParameters;
  public Repeat(List<List<Object>> parameters) {
    myParameters = parameters;
  }

  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    System.out.println(myParameters);
    double value = getParameterValue(myParameters.get(1), singleTurtle);
    List<Double> commands = new ArrayList<>();
    for(int i = 0; i<value ; i++) {
      commands = evaluateParameters(myParameters.get(0), singleTurtle);
    }
    return commands!=null ? commands.get(commands.size()-1) : 0;

  }
}
