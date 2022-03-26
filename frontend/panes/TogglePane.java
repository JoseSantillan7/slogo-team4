package slogo.frontend.panes;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import slogo.frontend.util.FrontendComponent;

/**
 * Interface for toggleable panes.
 *
 * @author Ritvik Janamsetty
 */
public abstract class TogglePane implements FrontendComponent {

  public static final boolean EXPAND = true;
  public static final boolean COLLAPSE = false;

  /**
   * Simple static method to toggle the visibility of the pane.
   *
   * @param content  the pane to toggle.
   * @param newState the new state of the pane.
   */
  protected void toggleVisibility(Node content, boolean newState) {
    content.setVisible(newState);
    content.setManaged(newState);
  }

  /**
   * Creates a scroll pane for a TogglePane pane and adds the desired content to it.
   *
   * @param content  the content to add to the scroll pane.
   * @param cssClass the css class to apply to the scroll pane.
   * @param height   the height of the scroll pane.
   * @return the scroll pane.
   */
  protected ScrollPane createScrollPane(Node content, String cssClass, double height) {
    ScrollPane pane = createScrollPane(content, cssClass);
    pane.setPrefHeight(height);
    return pane;
  }

  /**
   * Creates a scroll pane for a TogglePane pane and adds the desired content to it without
   * specified height.
   *
   * @param content  the content to add to the scroll pane.
   * @param cssClass the css class to apply to the scroll pane.
   * @return the scroll pane.
   */
  protected ScrollPane createScrollPane(Node content, String cssClass) {
    ScrollPane pane = new ScrollPane();
    // TODO: Change this to a CSS class than just a color string
    String style = "-fx-background-color: " + cssClass + ";";
    pane.setStyle(style);
    pane.setFitToWidth(true);
    pane.setContent(content);
    return pane;
  }

  /**
   * Toggles the visibility of the pane to new state. {@code true} expands the pane, {@code false}
   * shrinks it to the default size.
   *
   * @param newState the new state of the pane.
   */
  public abstract void toggleView(boolean newState);
}
