package slogo.model.commands;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import slogo.model.SingleTurtle;
import slogo.model.Turtle;

public class Command {
  public static final double EPSILON = 0.0001d;
  public static final double TRUE = 1.0;
  public static final double FALSE = 0.0;

  public static Object evaluateNewCommand(List<Object> commandAndValue){
    ResourceBundle commandResources = ResourceBundle.getBundle(Command.class.getPackageName()+ ".Commands");
    String command = commandResources.getString((String)commandAndValue.get(0));
    commandAndValue.remove(0);
    List<Object> parameters = new ArrayList<>(commandAndValue);

    try {
      Class<?> clazz = Class.forName(command);
      Constructor<?>[] ctor = clazz.getDeclaredConstructors( );
      return ctor[0].newInstance(parameters);
    }
    catch(ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e){
    }
    return 0;
  }

  public static boolean isCommand(Object obj){
    return obj instanceof Command;
  }

  protected double getParameterValue(Object parameter, SingleTurtle turtle){
    double myParam;
    if(isCommand(parameter)){
      myParam = ((Command) parameter).getReturnValue(turtle);
    }
    else {
      myParam = (Double)parameter;
    }
    return myParam;
  }

  protected List<Double> evaluateParameters(List<Object> parameters, SingleTurtle turtle){
    List<Double> retParameters = new ArrayList<>();
    System.out.println(parameters);
    for (Object parameter : parameters){
      retParameters.add(getParameterValue(parameter, turtle));
    }
    return retParameters;
  }

  public double getReturnValue(SingleTurtle singleTurtle) {
    return 0;
  }

}
