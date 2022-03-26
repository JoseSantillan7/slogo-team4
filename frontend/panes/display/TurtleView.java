package slogo.frontend.panes.display;


import static javafx.scene.paint.Color.BLACK;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import slogo.frontend.util.FrontendComponent;


public class TurtleView extends Node implements FrontendComponent {

  private static final int SIZE = 30;
  public static final int DURATION = 1;
  private final ImageView turtle;
  private final List<Path> pathList;
  private final SequentialTransition fullPath;
  private final Image inactiveImage;
  private final Consumer<Color> background;
  private Pane myPane;
  private Color currentColor = BLACK;
  private double startX;
  private Path displayedPaths;
  private double startY;
  private double currentX;
  private double currentY;
  private boolean penStatus;
  private Image activeImage;
  private int ID;

  public TurtleView(int id, Pane pane, double xOrigin, double yOrigin, Consumer<Color> backgroundColor) {
    super();
    ID = id;
    myPane = pane;
    background = backgroundColor;
    setId("turtle" + id);
    pathList = new ArrayList<>();
    penStatus = true;
    activeImage = new Image(
        Objects.requireNonNull(getClass().getResourceAsStream("/images/turtle.png")), SIZE, SIZE, true,
        true);

    turtle = new ImageView(activeImage);
    turtle.setRotate(90);
    inactiveImage = new Image(
        Objects.requireNonNull(getClass().getResourceAsStream("/images/graysquare.png")), SIZE, SIZE,
        true, true);

    fullPath = new SequentialTransition(turtle);
    implementToolTip();
    setPenStatusAction();
    setX(xOrigin);
    setY(yOrigin);
    startTurtle();

  }

  /**
   * Sets the background color of the pane.
   *
   * @param color the color to set the background to
   */
  public void setBackgroundColor(Color color) {
    background.accept(color);
  }

  public void changeTurtleImage(Image image) {
    turtle.setImage(image);
  }

  /**
   * Start the turtle
   */
  public void startTurtle() {

    myPane.getChildren().add(turtle);
    displayedPaths = new Path();
  }

  /**
   * Gets the path the turtle has taken
   *
   * @return The Full Path of the turtle
   */
  public SequentialTransition getPath() {
    return fullPath;
  }

  /**
   * Returns the turtle's shape.
   *
   * @return the turtle's shape
   */
  public Node getTurtle() {
    return turtle;
  }

  public void setTurtle(Image image) {
    if (penStatus) {
      turtle.setImage(image);
    }
    activeImage = image;
  }

  /**
   * Moves a turtle by the specified amount. Used by the public methods in the Turtle class.
   *
   * @param x the x value to move to
   * @param y the y value to move to
   */
  public void moveTurtle(Double x, Double y) {
    Path path = new Path();
    path.setFill(Color.BLACK);
    path.getElements().addAll(new MoveTo(currentX, currentY), new LineTo(x, y));
    myPane.getChildren().add(path);
    currentX = x;
    currentY = y;
    PathTransition transition = new PathTransition(Duration.seconds(DURATION), path, turtle);
    fullPath.getChildren().add(transition);

  }

  /**
   * Allow the turtle to start moving
   *
   * @param start the boolean that "animates" the path
   */
  public void startTurtleMove(Boolean start) {

    if (start) {

      fullPath.play();
    }
    fullPath.getChildren().clear();
  }

  /**
   * Turns the turtle to the specified angle.
   *
   * @param angle the angle to turn to
   */
  public void rotateTurtle(double angle) {
    RotateTransition transition = new RotateTransition(Duration.seconds(DURATION), turtle);
    transition.setByAngle(-angle);
    fullPath.getChildren().add(transition);
  }

  /**
   * Sets the pen status of the turtle.
   *
   * @param penStatus the pen status
   * @return the added path
   */
  public Path setPenStatus(boolean penStatus) {
    createNewPath();
    displayedPaths.setStroke(penStatus ? currentColor : Color.TRANSPARENT);

    if (penStatus) {
      turtle.setImage(activeImage);
    } else {
      turtle.setImage(inactiveImage);
    }
    this.penStatus = penStatus;
    return displayedPaths;

  }

  private void setPenStatusAction() {
    EventHandler<MouseEvent> handler1 = new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        setPenStatus(!penStatus);
      }
    };
    turtle.setOnMouseClicked(handler1);
  }

  /**
   * Sets the color of the current path.
   *
   * @param color the color to set
   */
  public void setCurrentPenColor(Color color) {
    currentColor = color;
    displayedPaths.setStroke(color);
  }

  /**
   * Sets the color of a new path.
   *
   * @param color the color to set
   */
  public Path setNewPenColor(Color color) {
    currentColor = color;
    createNewPath();
    displayedPaths.setStroke(color);
    return displayedPaths;
  }

  /**
   * Creates a new path.
   */
  private void createNewPath() {
    pathList.add(displayedPaths);
    displayedPaths = new Path();
  }

  /**
   * Clears all paths and creates a new path blank.
   */
  public void clearPaths(boolean penStatus) {
    pathList.forEach(path -> path.getElements().clear());
    pathList.clear();
    setPenStatus(penStatus);
  }

  /**
   * Sets the visibility of the turtle. The turtle will still be managed by the pane.
   *
   * @param visibility the visibility of the turtle
   */
  public void setVisibility(boolean visibility) {
    turtle.setVisible(visibility);
  }

  /**
   * Gets the X coordinate of this turtle
   *
   * @return the X coordinate
   */
  public double getX() {
    return turtle.getX();
  }

  /**
   * sets the X coordinate of this turtle
   *
   * @param newX Coordinate
   */
  private void setX(double newX) {
    currentX = newX;
    turtle.setX(newX);
  }

  /**
   * Gets the Y coordinate of this turtle
   *
   * @return the Y coordinate
   */
  public double getY() {
    return turtle.getY();
  }

  /**
   * sets the Y coordinate of this turtle
   *
   * @param newY Coordiante
   */
  private void setY(double newY) {
    currentY = newY;
    turtle.setY(newY);
  }

  /**
   * Gets whether the pen is active or inactive
   *
   * @return the Pen Status
   */
  public boolean penStatus() {
    return penStatus;
  }

  /**
   * Returns the node that represents this component.
   *
   * @return the node that represents this component.
   */
  @Override
  public Node getNode() {
    return myPane;
  }

  public void setPenWeight(double weight) {
    displayedPaths.setStrokeWidth(weight);
  }

  private void implementToolTip() {
    Tooltip tooltip = new Tooltip();

    tooltip.setText(String.format("ID: %d", ID));
    tooltip.setStyle("-fx-font: normal bold 14 Langdon; "
        + "-fx-base: white; "
        + "-fx-text-fill: orange;");

    Tooltip.install(turtle, tooltip);
  }

  public double getCurrentY() {
    return currentY;
  }

  public double getCurrentX() {return currentX;}

}
