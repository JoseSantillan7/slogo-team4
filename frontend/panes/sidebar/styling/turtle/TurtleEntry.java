package slogo.frontend.panes.sidebar.styling.turtle;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * Creates a ColorEntry used to create store data till a graphic is set. Allows for sorting by HSB
 * values.
 *
 * @param name   the name of the color
 * @param turtle the turtle to be set
 * @author Ritvik Janamsetty
 */
public record TurtleEntry(String name, Node turtle, boolean colorable) implements
    Comparable<TurtleEntry> {

  private static final int SIZE = 30;
  private static final Color DEFAULT_COLOR = Color.BLACK;

  /**
   * Creates a new TurtleEntry with the specified name and turtle. Override constructor for
   * initializing with a Shape;
   *
   * @param name        the name of the color
   * @param shapeTurtle the turtle to be set
   */
  public TurtleEntry(String name, Shape shapeTurtle) {
    this(name, initShape(shapeTurtle), true);
  }

  /**
   * Creates a new TurtleEntry with the specified name and turtle. Override constructor for
   * initializing with an image;
   *
   * @param name        the name of the color
   * @param imageTurtle the turtle to be set
   */
  public TurtleEntry(String name, ImageView imageTurtle) {
    this(name, initImageView(imageTurtle), false);
  }

  /**
   * Normalizes the specified Shape to a Node of a desired size.
   *
   * @param shapeTurtle the shape to be normalized
   * @return the normalized shape as a Node
   */
  private static Node initShape(Shape shapeTurtle) {
    Region container = new Region();
    container.setPrefSize(SIZE, SIZE);
    container.setMaxSize(SIZE, SIZE);
    container.setMinSize(SIZE, SIZE);
    container.setShape(shapeTurtle);
    BackgroundFill backgroundFill = new BackgroundFill(DEFAULT_COLOR, null, null);
    Background background = new Background(backgroundFill);
    container.setBackground(background);
    return container;
  }

  /**
   * Normalizes the specified ImageView to a Node of a desired size.
   *
   * @param imageTurtle the image to be normalized
   * @return the normalized image as a Node
   */
  private static Node initImageView(ImageView imageTurtle) {
    imageTurtle.setPreserveRatio(true);
    imageTurtle.setFitHeight(SIZE);
    imageTurtle.setFitWidth(SIZE);
    return imageTurtle;
  }

  /**
   * Checks if the specified ColorEntry can be colored
   *
   * @return true if the TurtleEntry can be colored
   */
  public boolean canColor() {
    return colorable;
  }

  /**
   * Compares this TurtleEntry to the specified TurtleEntry. The name of each TurtleEntry is
   * compared, and the result is negated if the name of this TurtleEntry is less than the specified
   * TurtleEntry's name.
   *
   * @param other the TurtleEntry to be compared.
   * @return a negative integer, zero, or a positive integer as this TurtleEntry is less than, equal
   * to, or greater than the specified TurtleEntry.
   */
  @Override
  public int compareTo(TurtleEntry other) {
    return name.compareTo(other.name);
  }

}
