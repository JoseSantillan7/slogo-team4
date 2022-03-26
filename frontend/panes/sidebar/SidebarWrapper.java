package slogo.frontend.panes.sidebar;

import static slogo.frontend.StylingAssistant.h1;
import static slogo.frontend.StylingAssistant.lightText;

import javafx.scene.Node;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import slogo.frontend.util.FrontendComponent;

/**
 * Container for holding a pane and its toggle buttons.
 *
 * @author Ritvik Janamsetty
 * @author Jose Santillan
 */
public class SidebarWrapper implements FrontendComponent {

  private final VBox wrapper;

  /**
   * Constructor for creating a container for a pane and its toggle buttons.
   *
   * @param name the name of the pane
   * @param pane the pane to be contained
   */
  public SidebarWrapper(String name, FrontendComponent pane) {
    Node title = lightText(h1(name));
    wrapper = new VBox();
    wrapper.getChildren().addAll(title, pane.getNode());
  }


  /**
   * Returns the node that represents this component.
   *
   * @return the node that represents this component.
   */
  @Override
  public Node getNode() {
    return wrapper;
  }
}
