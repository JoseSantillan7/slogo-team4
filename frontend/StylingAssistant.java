package slogo.frontend;

import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Interface used to style the nodes in the workspace.
 *
 * @author Ritvik Janamsetty
 */
public interface StylingAssistant {

  /**
   * Makes the label node have dark text.
   * @param node The node to style.
   * @return The styled node.
   */
  static Node darkText(Node node) {
    node.getStyleClass().add("dark-text");
    return node;
  }

  /**
   * Makes the label node have light text.
   * @param node The node to style.
   * @return The styled node.
   */
  static Node lightText(Node node) {
    node.getStyleClass().add("light-text");
    return node;
  }

  /**
   * Creates a heading label with the given text.
   * @param text The text to display in the label.
   * @return The label node
   */
  static Node h1(String text) {
    Label label = new Label(text.toUpperCase());
    label.getStyleClass().add("h1");
    return label;
  }

}
