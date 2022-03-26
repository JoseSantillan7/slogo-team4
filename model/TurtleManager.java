package slogo.model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import slogo.model.commands.Command;

public class TurtleManager implements Turtle{
  private List<Integer> activeTurtles = new ArrayList<>();
  private Map<Integer, SingleTurtle> allTurtles = new HashMap();
  private BiConsumer<Integer, List<Double>> onMove;
  private BiConsumer<Integer, Double> onRotate;
  private BiConsumer<Integer, Boolean> onPenChange;
  private final Consumer<Integer> addTurtleFunction = this::addTurtle;
  private final Consumer<Boolean> clearTurtleFunction = this::clearTurtles;
  private Consumer<Boolean> clearTurtleFront;

  private Method clearScreen;
  private Consumer<Integer> onCreateTurtle;

  public TurtleManager(Consumer<Boolean> clear, Consumer<Integer> createTurtle, BiConsumer<Integer, List<Double>> move, BiConsumer<Integer, Double> rotate, BiConsumer<Integer, Boolean>penChange){
    onMove = move;
    onRotate = rotate;
    onPenChange = penChange;
    onCreateTurtle = createTurtle;
    clearTurtleFront = clear;
    allTurtles = new HashMap<Integer, SingleTurtle>();
    addTurtle(1);
    activeTurtles.add(1);

  }

  public void addTurtle(int id){
    if(!activeTurtles.contains(id)){
      activeTurtles.add(id);
      allTurtles.putIfAbsent(id, new SingleTurtle(id, addTurtleFunction, clearTurtleFunction, onMove, onRotate, onPenChange));
      onCreateTurtle.accept(id);
    }

  }
  public void clearTurtles(boolean clear){
    activeTurtles.clear();
  }


  public void clearScreen(){
    //TODO add method to clear
  }

  public void reset() {

    activeTurtles.clear();
    activeTurtles.add(1);
    for(int turtleID : allTurtles.keySet()) {
      allTurtles.get(turtleID).setPosition(0,0, false);
      allTurtles.get(turtleID).setHead(0);
    }
  }
  public void start(List<Object> commands){
    clearTurtleFront.accept(true);
    for (Object command : commands){
      if(Command.isCommand(command)){
        for(int turtleID :new ArrayList<Integer> (activeTurtles)) {
          ((Command) command).getReturnValue(allTurtles.get(turtleID));
        }
      }
    }
  }



}
