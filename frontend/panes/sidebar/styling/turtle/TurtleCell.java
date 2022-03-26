package slogo.frontend.panes.sidebar.styling.turtle;

import javafx.scene.Node;
import slogo.frontend.util.FrontendComponent;
import slogo.frontend.util.NodeListCell;

/**
 * Creates a turtle entry for the turtle picker.
 *
 * @author Ritvik Janamsetty
 */
public class TurtleCell implements FrontendComponent {

  private final String name;
  private final Node turtle;
  private final Node container;

  /**
   * Create a new turtle entry for the color selector
   *
   * @param entry the turtle and its name
   */
  private TurtleCell(TurtleEntry entry) {
    name = entry.name();
    turtle = entry.turtle();
    container = NodeListCell.createCell(turtle, name);
  }

  /**
   * Constructs a new turtle entry and returns the node that represents it.
   *
   * @param entry the turtle and its name
   * @return the node that represents the turtle entry
   */
  public static Node makeEntry(TurtleEntry entry) {
    return new TurtleCell(entry).getNode();
  }


  /**
   * Returns the node that represents this turtle entry.
   *
   * @return the node that represents this turtle entry.
   */
  @Override
  public Node getNode() {
    return container;
  }
}
