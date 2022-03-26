package slogo.model.commands;

import java.util.List;
import slogo.model.SingleTurtle;

public class Tell extends Command{
  private List<List<Object>> myParameters;
  public Tell(List<List<Object>> parameters) {
    myParameters = parameters;
  }

  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    List<Double> turtles = evaluateParameters(myParameters.get(0), singleTurtle);
    singleTurtle.clearTurtles(true);
    for(double turtleID : turtles) {
      singleTurtle.addTurtle((int)turtleID);
    }
    return turtles.get(turtles.size()-1);
  }

}
