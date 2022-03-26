package slogo.frontend;

import static slogo.frontend.StylingAssistant.darkText;
import static slogo.frontend.StylingAssistant.h1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import slogo.frontend.buttons.ButtonSuper;
import slogo.frontend.panes.bottom.BottomPane;
import slogo.frontend.panes.display.ButtonPane;
import slogo.frontend.panes.display.GraphicPane;
import slogo.frontend.panes.display.TurtleCombo;
import slogo.frontend.panes.sidebar.Editor;
import slogo.frontend.panes.sidebar.SidebarPane;
import slogo.frontend.panes.sidebar.styling.Styling;

public class SLogoView {

  public static final int BOTTOMPANE_HEIGHT = 150;
  private static final int PADDING = 15;
  private static final int DOUBLE_PADDING = 2 * PADDING;
  private static final double TWO_THIRDS = 2.0 / 3.0;
  private static final double ONE_THIRD = 1 - TWO_THIRDS;
  private final Group myRoot;
  private final HBox mainContainer;
  private final Scene display;
  private double height;
  private double width;
  private List<ButtonSuper> buttonList;
  private ButtonPane buttonPane;
  private GraphicPane myGraphic;
  private VBox leftHalf;
  private VBox rightHalf;
  private BottomPane terminalHistory;
  private Editor editor;
  private double leftWidth;
  private double rightWidth;
  private double graphicHeight;
  private double terminalHeight;
  private ResourceBundle myResources;
  private Map<String, String> defaultPreferences;
  private Styling styler;

  /**
   * Initializes the view.
   *
   * @param width  The width of the view.
   * @param height The height of the view.
   */
  public SLogoView(double width, double height, ResourceBundle resources, Map<String, String> preferences) {
    defaultPreferences = preferences;
    myResources = resources;
    setSizes(width, height);
    myRoot = new Group();
    display = new Scene(myRoot, width, height);
    display.getStylesheets().add(getClass().getResource("yingyang.css").toExternalForm());
    buttonList = new ArrayList<>();
    mainContainer = new HBox();
    mainContainer.getChildren().addAll(initRightHalf(), initLeftHalf());
    myRoot.getChildren().add(mainContainer);
    buttonList = getNodesOfTypeButton(mainContainer, ButtonSuper.class);
  }

  /**
   * Sets all the heights of the panes.
   */
  private void setSizes(double width, double height) {
    this.width = width;
    this.height = height;
    leftWidth = width * TWO_THIRDS;
    rightWidth = width * ONE_THIRD;
    graphicHeight = height * TWO_THIRDS;
    terminalHeight = height * ONE_THIRD;
  }

  /**
   * Adds a list of commands to the editor.
   *
   * @param commands The list of commands to be added.
   */
  public void addToHistory(List<String> commands) {
    terminalHistory.getHistory().addToHistory(commands);
  }

  /**
   * Initializes the right half of the screen.
   *
   * @return The right half of the screen initialized.
   */
  private VBox initRightHalf() {
    rightHalf = new VBox();
    rightHalf.getStyleClass().add("right-half");
    initVBox(rightHalf, rightWidth);
    SidebarPane sidebar = new SidebarPane();
    rightHalf.getChildren().add(sidebar.getPane());
    editor = sidebar.getEditor();
    styler = sidebar.getStyling();
    return rightHalf;
  }

  /**
   * Initializes the left half of the screen.
   *
   * @return The left half of the screen initialized.
   */
  private VBox initLeftHalf() {
    leftHalf = new VBox();
    leftHalf.getStyleClass().add("left-half");
    Node graphicLabel = darkText(h1("Graphic"));
    Node messagesLabel = darkText((h1("Messages")));
    initVBox(leftHalf, leftWidth);

    terminalHistory = new BottomPane(display, BOTTOMPANE_HEIGHT, myResources);
    buttonPane = new ButtonPane(terminalHistory.getTurtleFact(), styler, this, myResources);

    myGraphic = new GraphicPane(leftWidth - DOUBLE_PADDING, graphicHeight - DOUBLE_PADDING,
        buttonPane.getTurtleCombo().createBiConsumer());

    terminalHistory.getPane().setPrefHeight(terminalHeight);

    leftHalf.getChildren()
        .addAll(graphicLabel, buttonPane.getNode(), myGraphic.getNode(), messagesLabel,
            terminalHistory.getNode());
    return leftHalf;
  }

  /**
   * Initializes the VBoxes for the left and right halves of the screen.
   *
   * @param pane  The VBox to be initialized.
   * @param width The width of the VBox.
   */
  private void initVBox(VBox pane, double width) {
    pane.setPrefHeight(height);
    pane.setPrefWidth(width);
    pane.setPadding(new Insets(PADDING));
  }

  /**
   * Starts the view and gets the scene.
   *
   * @return The scene of the view.
   */
  public Scene start() {
    return display;
  }

  /**
   * Step function for the view.
   *
   * @param timeElapsed The time elapsed since the last step.
   */
  public void step(double timeElapsed) {
    // Do nothing.
  }

  /**
   * Gets all nodes of a certain type from a pane.
   *
   * @param parent The pane to search through.
   * @param type   The type of node to search for.
   * @param <T>    The type of node to search for.
   * @return A list of nodes of the given type.
   */
  private <T> List<T> getNodesOfTypeButton(Pane parent, Class<T> type) {
    List<T> elements = new ArrayList<>();
    for (Node node : parent.getChildren()) {
      //TODO Change instaneof usage
      if (node instanceof Pane) {
        elements.addAll(getNodesOfTypeButton((Pane) node, type));
      } else if(node instanceof TurtleCombo) {
      } else if (type.isAssignableFrom(node.getClass())) {
        //noinspection unchecked
        elements.add((T) node);
      }
    }
    return Collections.unmodifiableList(elements);
  }

  /**
   * Gets the list of buttons in the view.
   *
   * @return The list of buttons in the view.
   */
  public List<ButtonSuper> getButtonList() {
    return buttonList;
  }

  /**
   * Extracts commands from the editor.
   *
   * @return The commands in the editor.
   */
  public String getCommands() {
    return null;
  }

  /**
   * Returns the editor pane from the sidebar
   *
   * @return The editor pane
   */
  public String getEditorText() {
    return editor.getText();
  }

  /**
   * Adds a command to the editor.
   *
   * @param text The command to be added. Gets the editor from the group
   */
  public void appendToEditor(String text) {
    editor.append(text);
  }

  /**
   * Gets the graphic pane currently being used by the stage
   *
   * @return GraphicPane
   */
  public GraphicPane getGraphicPane() {
    return myGraphic;
  }

  /**
   * Gets the editor currently being used by the stage
   *
   * @return The Editor in Question
   */
  public Editor getEditor() {
    return editor;
  }

  public Map<String, String> getPreferences() {
    return defaultPreferences;
  }
  /**
   * Gets the turtlecomboBox
   * @returns The TurtleComboBox
   */
  public TurtleCombo getTurtleCombo() {
    return buttonPane.getTurtleCombo();
  }

  public void addToTerminal(String message) {
    terminalHistory.addToTerminal(message);
  }
}
