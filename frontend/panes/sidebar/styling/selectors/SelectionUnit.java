package slogo.frontend.panes.sidebar.styling.selectors;

import java.util.function.Consumer;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import slogo.frontend.util.FrontendComponent;

/**
 * A selection unit is a UI component containing a label and a selector
 *
 * @param <T> the return type of the selector (e.g. ColorSelector returns Color)
 * @author Ritvik Janamsetty
 */
public abstract class SelectionUnit<T> implements FrontendComponent {

  protected static final double SELECTOR_WIDTH = 150;

  /**
   * Creates a new selection unit with the given label and selector, adds it to the given pane, and
   * defines an update function
   *
   * @param name     the label of the selection unit
   * @param comboBox the selector of the selection unit
   * @return the pane containing the selection unit
   */
  protected Node initContainer(String name, Node comboBox, Consumer<T> callback) {
    String buttonName = "Update";
    FlowPane pane = new FlowPane();
    pane.setHgap(5);
    Label nameLabel = new Label(name);
    Button update = new Button(buttonName);
    update.setOnAction(e -> callback.accept(getSelected()));
    pane.getChildren().addAll(nameLabel, comboBox, update);
    return pane;
  }

  protected Node initContainer(String name, Node comboBox) {
    String buttonName = "Update";
    FlowPane pane = new FlowPane();
    pane.setHgap(5);
    Label nameLabel = new Label(name);
    Button update = new Button(buttonName);
    pane.getChildren().addAll(nameLabel, comboBox, update);
    return pane;
  }

  /**
   * Gets the selected item
   *
   * @return the selected item
   */
  public abstract T getSelected();

}
