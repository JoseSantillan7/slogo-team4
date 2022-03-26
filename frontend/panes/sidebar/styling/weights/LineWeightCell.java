package slogo.frontend.panes.sidebar.styling.weights;

import javafx.scene.Node;
import javafx.scene.shape.Line;
import slogo.frontend.util.FrontendComponent;
import slogo.frontend.util.NodeListCell;

public class LineWeightCell implements FrontendComponent {

  private static final int WIDTH = 20;
  private final String name;
  private final double weight;
  private final Node container;

  /**
   * Create a new line weight entry for the new line weight selector
   *
   * @param entry the weight and its name
   */
  private LineWeightCell(LineWeightEntry entry) {
    name = entry.name();
    weight = entry.weight();
    container = createContent();
  }

  /**
   * Constructs a new line weight entry and returns the node that represents it.
   *
   * @param entry the weight and its name
   * @return the node that represents the line weight entry
   */
  public static Node makeEntry(LineWeightEntry entry) {
    return new LineWeightCell(entry).getNode();
  }

  /**
   * Creates the content for the line weight entry.
   *
   * @return the content for the line weight entry.
   */
  private Node createContent() {
    Line line = new Line(0, 0, WIDTH, 0);
    line.setStrokeWidth(weight);
    return NodeListCell.createCell(line, name);
  }

  /**
   * Returns the node that represents this line weight entry.
   *
   * @return the node that represents this line weight entry.
   */
  @Override
  public Node getNode() {
    return container;
  }


}
