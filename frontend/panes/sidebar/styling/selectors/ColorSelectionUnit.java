package slogo.frontend.panes.sidebar.styling.selectors;


import java.util.function.Consumer;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import slogo.frontend.panes.sidebar.styling.color.ColorSelector;
import slogo.frontend.util.FrontendComponent;

/**
 * A color selection unit that allows the user to select a color.
 */
public class ColorSelectionUnit extends SelectionUnit<Color> implements FrontendComponent {

  private final ColorSelector selector;
  private final Node container;

  /**
   * Creates a new ColorSelector with the given name, title, color.
   *
   * @param name The name of the ColorSelectionUnit.
   */
  public ColorSelectionUnit(String name, Consumer<Color> onChange) {
    this.selector = new ColorSelector(SELECTOR_WIDTH);
    this.container = initContainer(name, selector.getNode(), onChange);
  }

  /**
   * Gets the selected color of the ColorSelector.
   *
   * @return The selected color of the ColorSelector.
   */
  public Color getColor() {
    return selector.getSelected().color();
  }

  /**
   * Gets the node of the ColorSelectionUnit.
   *
   * @return The node of the ColorSelectionUnit.
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
  public Color getSelected() {
    return selector.getSelected().color();
  }
}
