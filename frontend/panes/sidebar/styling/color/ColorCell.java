package slogo.frontend.panes.sidebar.styling.color;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import slogo.frontend.util.FrontendComponent;
import slogo.frontend.util.NodeListCell;

/**
 * Creates a color entry for the color picker.
 *
 * @author Ritvik Janamsetty
 */
public class ColorCell implements FrontendComponent {


  private static final int HEIGHT = 10;
  private static final int WIDTH = 20;
  private final String name;
  private final Color color;
  private final Node container;

  /**
   * Create a new color entry for the color selector
   *
   * @param entry the color and its name
   */
  private ColorCell(ColorEntry entry) {
    name = entry.name();
    color = entry.color();
    container = createContent();

  }

  /**
   * Constructs a new color entry and returns the node that represents it.
   *
   * @param entry the color and its name
   * @return the node that represents the color entry
   */
  public static Node makeEntry(ColorEntry entry) {
    return new ColorCell(entry).getNode();
  }

  /**
   * Creates the content for the color entry.
   *
   * @return the content for the color entry.
   */
  private Node createContent() {
    Rectangle rectangle = new Rectangle(WIDTH, HEIGHT);
    rectangle.setFill(color);
    return NodeListCell.createCell(rectangle, name);
  }

  /**
   * Returns the node that represents this color entry.
   *
   * @return the node that represents this color entry.
   */
  @Override
  public Node getNode() {
    return container;
  }

}
