package slogo.frontend.panes.sidebar.styling;

import javafx.scene.Node;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import slogo.frontend.Turtleable;
import slogo.frontend.panes.TogglePane;
import slogo.frontend.panes.display.TurtleView;
import slogo.frontend.panes.sidebar.styling.selectors.ColorSelectionUnit;
import slogo.frontend.panes.sidebar.styling.selectors.TurtleSelectionUnit;
import slogo.frontend.panes.sidebar.styling.selectors.WeightSelectionUnit;
import slogo.frontend.util.FrontendComponent;

/**
 * Controls the styling pane
 *
 * @author Jose Santillan
 * @author Ritvik Janamsetty
 */
public class Styling extends TogglePane implements Turtleable, FrontendComponent {

  private final VBox container;
  private TurtleView currentTurtle;
  private int currentTurtleID;
  private boolean notInitialized = false;


  /**
   * Creates a new Styling object.
   */
  public Styling() {
    ColorSelectionUnit backgroundColor = new ColorSelectionUnit("Background Color", (color) -> currentTurtle.setBackgroundColor(color));
    ColorSelectionUnit penColor = new ColorSelectionUnit("Pen Color", (color) -> currentTurtle.setNewPenColor(color));
    WeightSelectionUnit penWeight = new WeightSelectionUnit((weight) -> currentTurtle.setPenWeight(weight));
    TurtleSelectionUnit turtle = new TurtleSelectionUnit();
    container = new VBox();
    VBox.setVgrow(container, Priority.ALWAYS);
    container.getChildren()
        .addAll(backgroundColor.getNode(), penColor.getNode(), penWeight.getNode(),
            turtle.getNode());
    container.getStyleClass().add("pane");
  }

  /**
   * Toggles the visibility of the pane to new state.
   *
   * @param newState the new state of the pane.
   */
  @Override
  public void toggleView(boolean newState) {
    toggleVisibility(container, newState);
  }

  /**
   * Returns the node that represents this component.
   *
   * @return the node that represents this component.
   */
  @Override
  public Node getNode() {
    return container;
  }

  /**
   * Get and sets the turtle view of the turtle.
   *
   * @param id     the id of the turtle
   * @param turtle the turtle view
   */
  @Override
  public void accessTurtle(int id, TurtleView turtle) {
    notInitialized = true;
    currentTurtle = turtle;
    currentTurtleID = id;
  }
}
