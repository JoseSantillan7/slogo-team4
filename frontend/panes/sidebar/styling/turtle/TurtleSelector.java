package slogo.frontend.panes.sidebar.styling.turtle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import slogo.frontend.util.FrontendComponent;
import slogo.frontend.util.NodeComboBox;

/**
 * A ComboBox that displays a list of turtles.
 *
 * @author Ritvik Janamsetty
 */
public class TurtleSelector implements FrontendComponent {

  private static final String STYLING_PACKAGE = TurtleSelector.class.getPackageName();
  private static final String FILENAME = ".DefaultTurtles";

  private final ResourceBundle defaultTurtles;
  private final Set<TurtleEntry> turtleEntries;
  private final List<TurtleEntry> themeTurtles;
  private final ComboBox<TurtleEntry> selector;

  /**
   * Creates a TurtleSelector with the default line weights specified in the resource file.
   */
  public TurtleSelector(double width) {
    defaultTurtles = ResourceBundle.getBundle(STYLING_PACKAGE + FILENAME);
    selector = new NodeComboBox<>(TurtleCell::makeEntry);
    turtleEntries = new HashSet<>();
    defaultTurtles.keySet().forEach(key -> turtleEntries.add(
        TurtleReflector.invokeReflector(key, defaultTurtles.getString(key))));
    selector.getItems().addAll(turtleEntries);
    selector.setPrefWidth(width);
    themeTurtles = new ArrayList<>();
  }

  /**
   * Returns the ComboBox used to select a turtle.
   *
   * @return the ComboBox used to select a turtle
   */
  @Override
  public Node getNode() {
    return selector;
  }

  /**
   * Returns the selected turtle.
   *
   * @return the selected turtle
   */
  public TurtleEntry getValue() {
    return selector.getValue();
  }

  /**
   * Adds a new turtle to the selector.
   *
   * @param name  the name of the turtle
   * @param image the image of the turtle
   */
  public void addTurtle(String name, ImageView image) {
    TurtleEntry entry = new TurtleEntry(name, image);
    themeTurtles.add(entry);
    selector.getItems().add(entry);
  }

  /**
   * Gets a list of added turtles.
   *
   * @return a list of added turtles
   */
  public List<TurtleEntry> getThemeTurtles() {
    return themeTurtles;
  }
}


