package slogo.controller;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import slogo.frontend.SLogoView;
import slogo.frontend.buttons.ButtonSuper;
import slogo.frontend.panes.display.GraphicPane;
import slogo.frontend.panes.display.TurtleCombo;
import slogo.frontend.panes.sidebar.Editor;

/**
 * The ViewController class is responsible for controlling the view of the SLogo application.
 *
 * @author Jose Santillan
 * @author Ritvik Janamsetty
 */
public class ViewController {

  private static final double SIZE = 1080;
  private static final String TITLE = "SLogo";
  private static final int FRAMES_PER_SECOND = 60;
  private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  private final SLogoView root;
  private ResourceBundle myResources;
  private double myPaneSize;

  /**
   * Constructs a new ViewController and sets the root to the given view.
   * @param resources
   */
  public ViewController(ResourceBundle resources) {
    init(resources);
    root = new SLogoView(myPaneSize, myPaneSize, myResources, null);
  }

  /**
   * Constructs a new ViewController and sets the root to the given view and also sets the preferences passed
   * @param resources
   * @param preferences
   */
  public ViewController(ResourceBundle resources, Map<String, String> preferences) {
    init(resources);
    root = new SLogoView(myPaneSize, myPaneSize, myResources, preferences);
  }

  /**
   * Constructs the similarities between the constructors
   * @param resources
   */
  private void init(ResourceBundle resources) {
    myResources = resources;
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    double width = Math.min(primaryScreenBounds.getWidth(), SIZE);
    double height = Math.min(primaryScreenBounds.getHeight(), SIZE);
    myPaneSize = Math.min(width, height);
  }

  /**
   * Gets all buttons in the view.
   *
   * @return all buttons in the view
   */
  public List<ButtonSuper> getButtons() {
    return root.getButtonList();
  }

  /**
   * Gets all commands in the view.
   *
   * @return all commands in the view
   */
  public String getCommands() {
    return root.getCommands();
  }

  /**
   * Adds a command to the view history.
   *
   * @param commands the command to add
   */
  public void addToHistory(List<String> commands) {
    root.addToHistory(commands);
  }

  /**
   * Gets the editor in the view.
   *
   * @return the editor in the view
   */
  public String getEditorText() {
    return root.getEditorText();
  }

  /**
   * Appends the given string to the editor.
   *
   * @param text the string to append
   */
  public void appendToEditor(String text) {
    root.appendToEditor(text);
  }

  /**
   * Sets up JavaFX and starts the view.
   *
   * @param stage the stage to start the view on
   */
  public void startView(Stage stage) {
    setUpStage(stage);
    setUpAnimation();
  }

  /**
   * Attach scene to the stage and display it
   * <p> To implement the change listeners for auto-scaling of the view window, Ritvik visited this
   * <a href="https://stackoverflow.com/questions/18998671/set-height-and-width-of-stage-and-scene-in-javafx">StackOverflow
   * post</a>.</p>
   *
   * @param stage the stage to display the scene on
   */
  private void setUpStage(Stage stage) {
    stage.setTitle(TITLE);
    Scene scene = root.start();
    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();
  }

  /**
   * Attaches "game loop" to timeline to play it (basically just calling step() method repeatedly
   * forever)
   */
  private void setUpAnimation() {
    Timeline animation = new Timeline();
    animation.setCycleCount(Animation.INDEFINITE);
    animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> {
      root.step(SECOND_DELAY);
    }));
    animation.play();
  }

  /**
   * Returns the graphic pane currently being used by the stage
   *
   * @return The Graphic Pane
   */
  public GraphicPane getGraphicPane() {
    return root.getGraphicPane();
  }

  /**
   * Gets the Editor being used by the current stage
   *
   * @return The Editor
   */
  public Editor getEditor() {
    return root.getEditor();
  }

  /**
   * Creates a BiConsumer using the getGraphicPane moveTurtle method
   *
   * @return A BiConsumer using the moveTurtle method
   * @see GraphicPane#startTurtle(int, Boolean)
   */
  public BiConsumer<Integer, List<Double>> onMove() {
    return getGraphicPane()::moveTurtle;
  }

  /**
   * Creates a BiConsumer using the GraphicPane rotateTurtle method
   *
   * @return A BiConsumer using the rotateTurtle method
   * @see GraphicPane#rotateTurtle(int, double)
   */
  public BiConsumer<Integer, Double> onRotate() {
    return getGraphicPane()::rotateTurtle;
  }

  /**
   * Creates a BiConsumer using the GraphicPane setPenStatus method
   *
   * @return A BiConsumer using the setPenStatus method
   * @see GraphicPane#setPenStatus(int, boolean)
   */
  public BiConsumer<Integer, Boolean> onPenChange() {
    return getGraphicPane()::setPenStatus;
  }

  /**
   * Creates a BiConsumer using the GraphicPane startTurtle method
   *
   * @return A BiConsumer using the startTurtle method
   * @see GraphicPane#startTurtle(int, Boolean)
   */
  public Consumer<Boolean> startTurtle() {
    return getGraphicPane()::startTurtle;
  }

  public Consumer<Boolean> clearTurtle() {
    return getGraphicPane()::clearTurtles;
  }

  /**
   * Gets the turtlecomboBox
   * @returns The TurtleComboBox
   */
  public TurtleCombo getTurtleCombo() {
    return root.getTurtleCombo();
  }
  public Consumer<Integer> createTurtle() {
    return getGraphicPane()::createTurtle;
  }

  public void addToTerminal(String message) {
    root.addToTerminal(message);
  }
}
