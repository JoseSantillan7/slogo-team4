package slogo.model.commands.turtle_queries;

import java.util.List;
import slogo.model.SingleTurtle;
import slogo.model.Turtle;
import slogo.model.commands.Command;

public class XCoordinate extends Command {


  public XCoordinate(List<Double> parameter){
  }

  @Override
  public double getReturnValue(SingleTurtle singleTurtle){
    return singleTurtle.getX();
  }
}
