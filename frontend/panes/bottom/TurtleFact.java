package slogo.frontend.panes.bottom;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import slogo.frontend.Turtleable;
import slogo.frontend.panes.TogglePane;
import slogo.frontend.panes.display.TurtleView;
import slogo.frontend.util.FrontendComponent;

public class TurtleFact extends TogglePane implements Turtleable, FrontendComponent {

  private static final String PRE_DETERMINED_TURTLE = "ID: %d  X: %f  Y: %f  Angle: %f  Active: %b";
  private static final String PRE_DETERMINED_PEN = "Pen Up: %s  Color: %s  Thickness: %d";

  private TextField turtleFacts;
  private TextField penFacts;

  private VBox textArea;

  public TurtleFact() {
    textArea = new VBox();
    turtleFacts = new TextField("No Turtle Selected");
    penFacts = new TextField("No Pen Selected");
    textArea.getChildren().addAll(turtleFacts, penFacts);
  }

  public Pane getPane() {
    return textArea;
  }

  public void toggleView(boolean newState) {
    toggleVisibility(textArea, newState);
  }

  /**
   * Updates the turtle facts.
   *
   * @param id     the id of the turtle
   * @param turtle the turtle view
   */
  public void accessTurtle(int id, TurtleView turtle) {
    textArea.getChildren().clear();
    turtleFacts = new TextField(
        String.format(PRE_DETERMINED_TURTLE, id, turtle.getCurrentX(), turtle.getCurrentY(), turtle.getRotate(),
            turtle.penStatus()));
    penFacts = new TextField(String.format(PRE_DETERMINED_PEN, "temp", "temp", 4));
    textArea.getChildren().addAll(turtleFacts, penFacts);
  }


  /**
   * Returns the node that represents this component.
   *
   * @return the node that represents this component.
   */
  @Override
  public Node getNode() {
    return textArea;
  }
}
