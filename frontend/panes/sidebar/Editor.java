package slogo.frontend.panes.sidebar;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import slogo.frontend.panes.Editable;
import slogo.frontend.panes.TogglePane;
import slogo.frontend.util.FrontendComponent;

/**
 * Controls the editor pane
 *
 * @author Jose Santillan
 * @author Ritvik Janamsetty
 */
public class Editor extends TogglePane implements Editable, FrontendComponent {

  public static final String NEW_LINE = "\n";
  private final TextArea editor;
  private final VBox container;

  /**
   * Creates a new editor pane including a text area
   */
  public Editor() {
    editor = new TextArea();
    container = new VBox();

    container.getChildren().addAll(editor);
  }

  /**
   * Gets the test in the editor pane
   *
   * @return the text in the editor pane
   */
  @Override
  public String getText() {
    return editor.getText();
  }


  /**
   * Toggles the visibility of the pane to new state. {@code true} expands the pane, {@code false}
   * shrinks it to the default size.
   *
   * @param newState the new state of the pane.
   */
  @Override
  public void toggleView(boolean newState) {
    // TODO: Implement expand/collapse functionality
  }

  /**
   * Replaces the text in the editor pane with the new text
   *
   * @param s the new text to replace the old text with
   */
  @Override
  public void append(String s) {
    editor.setText(s);
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
