package slogo.frontend.panes.sidebar.styling.selectors;

import java.util.function.Consumer;
import javafx.scene.Node;
import slogo.frontend.panes.sidebar.styling.weights.LineWeightSelector;
import slogo.frontend.util.FrontendComponent;

/**
 * Creates a UI component to select the weight of a line.
 *
 * @author Ritvik Janamsetty
 */
public class WeightSelectionUnit extends SelectionUnit<Double> implements FrontendComponent {

  private static final String NAME = "Line Weight";
  private final LineWeightSelector selector;
  private final Node container;

  /**
   * Creates a weight selection unit.
   */
  public WeightSelectionUnit(Consumer<Double> onChange) {
    this.selector = new LineWeightSelector(SELECTOR_WIDTH);
    container = initContainer(NAME, selector.getNode(), onChange);
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
   * Gets the selected item
   *
   * @return the selected item
   */
  @Override
  public Double getSelected() {
    return selector.getValue();
  }
}
