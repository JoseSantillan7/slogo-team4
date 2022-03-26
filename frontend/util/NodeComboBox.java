package slogo.frontend.util;

import java.util.function.Function;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;

/**
 * A ComboBox that can be used to display a Node.
 *
 * @param <T> the type of the item to be displayed
 * @author Ritvik Janamsetty
 */
public class NodeComboBox<T> extends ComboBox<T> {

  private final Function<T, Node> setGraphicCallback;

  /**
   * Creates a subclass of ComboBox that is capable of displaying a Node. Constructs a new {@code
   * NodeComboBox} with the given {@code callback}.
   *
   * @param callback the callback to use to set the graphic that returns a Node
   */
  public NodeComboBox(Function<T, Node> callback) {
    super();
    setGraphicCallback = callback;
    setCellFactory(param -> new NodeListCell<>(setGraphicCallback));
    setButtonCell(new NodeListCell<>(setGraphicCallback));
  }

}
