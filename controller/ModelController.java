package slogo.controller;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import slogo.model.SLogoModel;
import slogo.model.SLogoModelInterface;

public class ModelController {

  private SLogoModelInterface myModel;

  public ModelController(Consumer<Boolean> clear,
      Consumer<Integer> createTurtle, BiConsumer<Integer, List<Double>> onMove,
      BiConsumer<Integer, Double> onRotate, BiConsumer<Integer, Boolean> onPenChange,
      Consumer<Boolean> startTurtle) {
    myModel = new SLogoModel(clear, createTurtle, onMove, onRotate, onPenChange, startTurtle);
  }

  public void start(String commands) {
    myModel.parseCommands(commands);
  }


  public void reset(){
    myModel.reset();
  }
}
