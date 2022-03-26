package slogo.model;


import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import slogo.model.commands.Command;

import slogo.model.parser.Parser;

public class SLogoModel implements SLogoModelInterface{

  private final Consumer<Boolean> startTurtle;
  private TurtleManager turtleManager;



  public SLogoModel(Consumer<Boolean> clear, Consumer<Integer> createTurtle, BiConsumer<Integer, List<Double>> onMove, BiConsumer<Integer, Double> onRotate,
      BiConsumer<Integer, Boolean> onPenChange, Consumer<Boolean> startTurtle){

    //TODO NEED TO GET RID OF THIS HARDCODED VALUE (MULTIPLE TURTLES)
    turtleManager = new TurtleManager(clear, createTurtle, onMove, onRotate, onPenChange);

    this.startTurtle = startTurtle;

  }

  public void parseCommands(String commandText){
    Parser myParser = new Parser(turtleManager, commandText, "English");
    List<Object> commands = myParser.getLres();
    turtleManager.start(commands);
    startTurtle.accept(true);
    turtleManager.reset();

  }

  @Override
  public void reset() {
    turtleManager.reset();
  }

}
