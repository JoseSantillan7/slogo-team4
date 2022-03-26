package slogo.frontend.panes.sidebar;

import javafx.scene.layout.VBox;
import slogo.frontend.panes.sidebar.documentation.Documentation;
import slogo.frontend.panes.sidebar.styling.Styling;

/**
 * Creates a sidebar pane that includes components that can be toggled on and off.
 *
 * @author Ritvik Janamsetty
 */
public class SidebarPane {

  private Editor editor;
  private Styling styling;
  private VBox pane;

  /**
   * Create a sidebar pane with all the components.
   */
  public SidebarPane() {
    initComponents();
  }

  /**
   * Initialize all the components of the sidebar pane.
   */
  private void initComponents() {
    pane = new VBox();
    editor = new Editor();
    styling = new Styling();
    SidebarWrapper editorWrapper = new SidebarWrapper("Editor", editor);
    SidebarWrapper displayWrapper = new SidebarWrapper("Styling", styling);
    SidebarWrapper documentationWrapper = new SidebarWrapper("Documentation", new Documentation());
    pane.getChildren()
        .addAll(editorWrapper.getNode(), displayWrapper.getNode(), documentationWrapper.getNode());
  }

  /**
   * Get the pane of the sidebar.
   *
   * @return the pane of the sidebar.
   */
  public VBox getPane() {
    return pane;
  }

  /**
   * Get the editor in the sidebar.
   *
   * @return the editor in the sidebar.
   */
  public Editor getEditor() {
    return editor;
  }

  /**
   * Get the styling in the sidebar.
   * @return the styling in the sidebar.
   */
  public Styling getStyling() {
    return styling;
  }

}


