package slogo.frontend.panes.bottom;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import slogo.frontend.panes.Editable;
import slogo.frontend.panes.TogglePane;
import slogo.frontend.util.FrontendComponent;


/**
 * Controls the history pane
 *
 * @author Jose Santillan
 * @author Ritvik Janamsetty
 */
public class History extends TogglePane implements FrontendComponent, Editable {

  public static final double HEIGHT = 200;
  private static final String COLOR = "Aqua";
  private static final String NEW_HISTORY = "History Segment ";
  private static final String NEW_LINE = "\n";
  private final List<List<String>> historyTracker;
  private final VBox pane;
  private final ScrollPane container;

  public History() {
    historyTracker = new ArrayList<>();
    pane = new VBox();
    container = createScrollPane(pane, COLOR, HEIGHT);
  }

  /**
   * Toggles the visibility of the pane to new state.
   *
   * @param newState the new state of the pane.
   */
  @Override
  public void toggleView(boolean newState) {
    toggleVisibility(container, newState);
  }

  /**
   * Adds the given list of strings to the history.
   *
   * @param addition the list of strings to add to the history.
   */
  public void addToHistory(List<String> addition) {
    historyTracker.add(addition);
    StringBuilder sb = new StringBuilder();
    sb.append(NEW_HISTORY).append(historyTracker.size()).append(NEW_LINE);
    addition.forEach(e -> {
      sb.append(e);
      sb.append(NEW_LINE);
    });
    sb.append(NEW_LINE);
    pane.getChildren().add(new Text(sb.toString()));
  }

  /**
   * Gets the text in the text field in the pane and returns it.
   *
   * @return the text in the text field in the pane.
   */
  @Override
  public String getText() {
    StringBuilder sb = new StringBuilder();
    int index = 1;
    for (List<String> list : historyTracker) {
      sb.append(String.format("History #%d", index)).append(NEW_LINE);
      for (String s : list) {
        sb.append(s).append(NEW_LINE);
      }
      index += 1;
      sb.append(NEW_LINE);
    }
    return sb.toString();
  }

  /**
   * Sets the text in the text field in the pane to the given text.
   *
   * @param s the text to set the text field to
   */
  public void append(String s) {
    addToHistory(List.of(s.split("\n")));
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
