package slogo.frontend.panes.sidebar.styling.selectors;

import javafx.scene.Node;
import slogo.frontend.panes.sidebar.styling.turtle.TurtleSelector;
import slogo.frontend.util.FrontendComponent;

public class TurtleSelectionUnit extends SelectionUnit<Node> implements FrontendComponent {

  private static final String NAME = "Turtle";
  private final TurtleSelector selector;
  private final Node container;

  /**
   * Creates a weight selection unit.
   */
  public TurtleSelectionUnit() {
    this.selector = new TurtleSelector(SELECTOR_WIDTH);
    container = initContainer(NAME, selector.getNode());
  }

  /**
   * Gets the selected item
   *
   * @return the selected item
   */
  @Override
  public Node getSelected() {
    return selector.getValue().turtle();
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
}
