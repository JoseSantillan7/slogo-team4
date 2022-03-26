package slogo.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 *This Class holds the
 */

public class SingleTurtle implements Turtle{

  private List<PropertyChangeListener> listeners = new ArrayList<>();
  private double myX;
  private double myY;
  private double myHeadDirection;
  private boolean isTurtleVisible;
  private boolean isPenUp;
  private BiConsumer<Integer, List<Double>> onMove;
  private BiConsumer<Integer, Double> onRotate;
  private BiConsumer<Integer, Boolean> onPenChange;
  private Consumer<Integer> addTurtleFunction;
  private Consumer<Boolean> clearTurtleFunction;
  private Method clearScreen;
  private final int ID;


  public SingleTurtle(int id, Consumer<Integer> addTurtle, Consumer<Boolean> clearTurtles, BiConsumer<Integer, List<Double>> move, BiConsumer<Integer, Double> rotate, BiConsumer<Integer, Boolean>penChange){
    ID = id;
    onMove = move;
    onRotate = rotate;
    this.addTurtleFunction = addTurtle;
    this.clearTurtleFunction = clearTurtles;
    onPenChange = penChange;
    myX = 0;
    myY = 0;
    myHeadDirection = 0;
    isPenUp = false;
    isTurtleVisible = true;
  }

  public void addTurtle(int id){
    addTurtleFunction.accept(id);
  }

  public void clearTurtles(boolean clear){
    clearTurtleFunction.accept(clear);
  }


  public void setPosition(double newX, double newY, boolean show){
    myY = newY;
    myX = newX;
    if(show) {
      onMove.accept(ID, List.of(newX, newY));
    }

  }
  public void setHead(double head){
    double prevHead = myHeadDirection;
    myHeadDirection = head;
    onRotate.accept(ID, head-prevHead);
  }
  public void setPenUp(boolean penUp){
    isPenUp = penUp;

  }
  public void setTurtleVisible(boolean visible){isTurtleVisible = visible;}

  public boolean getIsTurtleVisible(){return isTurtleVisible;}

  public boolean getIsPenUp(){return isPenUp;}

  public double getX(){return myX;}

  public double getY(){return myY;}

  public double getHead(){return myHeadDirection;}

  public void clearScreen(){
    //TODO add method to clear
  }

  public void reset() {
    myX = 0;
    myY = 0;
    myHeadDirection = 0;
  }
}
