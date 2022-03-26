package slogo.frontend.panes.bottom;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import slogo.frontend.panes.Editable;
import slogo.frontend.panes.TogglePane;
import slogo.frontend.util.FrontendComponent;

/**
 * The Terminal class is a pane that displays any output from the backend.
 *
 * @author Jose Santillian
 * @author Ritvik Janamsetty
 */
public class Terminal extends TogglePane implements Editable, FrontendComponent {

  public static final int HEIGHT = 200;
  private static final String COLOR = "Black";
  private final ScrollPane pane;
  private final TextArea terminal;

  public Terminal() {
    VBox terminalBox = new VBox();
    VBox.setVgrow(terminalBox, Priority.ALWAYS);
    terminal = new TextArea();
    terminal.setEditable(false);
    terminalBox.getChildren().add(terminal);
    pane = createScrollPane(terminalBox, COLOR, HEIGHT);
  }

  /**
   * Toggles the visibility of the pane to new state.
   *
   * @param newState the new state of the pane.
   */
  @Override
  public void toggleView(boolean newState) {
    toggleVisibility(pane, newState);
  }

  /**
   * Returns the node that represents this component.
   *
   * @return the node that represents this component.
   */
  @Override
  public Node getNode() {
    return pane;
  }

  /**
   * Gets the text in the text field in the pane and returns it.
   *
   * @return the text in the text field
   */
  @Override
  public String getText() {
    // TODO: IMPLEMENT THIS FUNCTION!!
    return "";
  }

  /**
   * Sets the text in the text field in the pane to the given text.
   *
   * @param s the text to set the text field to
   */
  @Override
  public void append(String s) {
    // TODO: IMPLEMENT THIS FUNCTION!!
    try {
      terminal.appendText(s);
    } catch (Exception ex) {

    }
  }
}
