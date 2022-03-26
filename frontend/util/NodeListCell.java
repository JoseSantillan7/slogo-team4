package slogo.frontend.util;

import java.util.function.Function;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

/**
 * A ListCell that can be used to display a Node.
 *
 * @param <T> the type of the item to be displayed
 * @author Ritvik Janamsetty
 */
public class NodeListCell<T> extends ListCell<T> {

  private static final Color DEFAULT_COLOR = Color.BLACK;
  private static final int PADDING = 5;
  private final Function<T, Node> setGraphicCallback;

  /**
   * Creates a subclass of ListCell that is capable of displaying a Node. Constructs a new {@code
   * NodeListCell} with the given {@code callback}.
   *
   * @param callback the callback to use to set the graphic that returns a Node
   */
  public NodeListCell(Function<T, Node> callback) {
    super();
    setGraphicCallback = callback;
    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
  }

  /**
   * Returns a new node that contains the display node and the text of the item.
   *
   * @param node the node to display
   * @param name the name of the node
   * @return a new {@code FlowPane} with the given {@code node} and {@code label} as children
   */
  public static Node createCell(Node node, String name) {
    FlowPane content = new FlowPane();
    content.setHgap(PADDING);
    Label label = new Label(name);
    label.setTextFill(DEFAULT_COLOR);
    content.getChildren().addAll(node, label);
    return content;
  }

  /**
   * Overrides the superclass method to set the graphic to the node provided by the callback
   * function.
   *
   * @param item  the item to display
   * @param empty whether the cell is empty
   */
  @Override
  protected void updateItem(T item, boolean empty) {
    super.updateItem(item, empty);
    if (item == null || empty) {
      setGraphic(null);
    } else {
      setGraphic(setGraphicCallback.apply(item));
    }
  }
}
