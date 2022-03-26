package slogo.frontend.panes.display;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import javafx.scene.control.ComboBox;
import slogo.frontend.Turtleable;

public class TurtleCombo extends ComboBox {

  private Map<Integer, TurtleView> myTurtleMap;
  private Turtleable myFacts;
  private  Turtleable myStyler;

  public TurtleCombo(Turtleable facts, Turtleable styler) {
    myTurtleMap = new HashMap<>();
    myFacts = facts;
    myStyler = styler;
    init();
  }

  private void init() {
    setValue("TurtleSelector");
    setOnAction(event -> {
      TurtleView turtle = myTurtleMap.get((Integer) getValue());
      myFacts.accessTurtle((int) getValue(), turtle);
      myStyler.accessTurtle((int) getValue(), turtle);
    });
  }

  public void addTurtle(int id, TurtleView turtle) {
    boolean check = true;
    if (myTurtleMap.containsKey(id)) check = false;
    myTurtleMap.put(id, turtle);
    if (check) getItems().addAll(id);

  }
  public BiConsumer<Integer, TurtleView> createBiConsumer() {
    return this::addTurtle;
  }
}
