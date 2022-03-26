package slogo.frontend.panes.display;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import slogo.frontend.util.FrontendComponent;
import slogo.model.SingleTurtle;

/**
 * Controls the graphic pane
 *
 * @author Jose Santillan
 * @author Ritvik Janamsetty
 */
public class GraphicPane implements FrontendComponent {

  private static final String COLOR = "White";
  private static final double MAX_POS = 127;
  private static final double HALF = 0.5;
  private final Map<Integer, TurtleView> turtleViewMap;
  private Pane pane;
  private int currentID = 1;
  private double width;
  private double height;
  private double xOrigin;
  private double yOrigin;
  private double xDisplacement;
  private double yDisplacement;

  private BiConsumer<Integer, TurtleView> newTurtle;

  public GraphicPane(double width, double height, BiConsumer<Integer, TurtleView> consumer) {
    newTurtle = consumer;
    initPane(width, height);
    setSizes();
    this.width = width;
    this.height = height;
    turtleViewMap = new LinkedHashMap<>();
    xDisplacement = 0;
    yDisplacement = 0;
  }

  /**
   * Sets all the instance variables based on the size of the pane
   */
  private void setSizes() {
    this.width = pane.getPrefWidth();
    this.height = pane.getPrefHeight();
    xOrigin = this.width * HALF;
    yOrigin = this.height * HALF;

  }

  /**
   * Initializes the pane
   */
  private void initPane(double width, double height) {
    pane = new Pane();
    // TODO: Change this to a CSS class than just a color string
    String style = String.format("-fx-background-color: %s; -fx-border-color: %s;", COLOR, "black");
    pane.setStyle(style);
    pane.setPrefSize(width, height);
  }

  /**
   * Adds a turtle to the graphic pane
   *
   * @param id the id of the turtle
   */
  public void createTurtle(int id) {
    TurtleView turtle = new TurtleView(id, pane, xOrigin, yOrigin, (color) -> setBackground(color));
    turtleViewMap.put(id, turtle);
    newTurtle.accept(id, turtle);
  }

  /**
   * Function to set the color of the background
   * @param color the color to set the background to
   */
  private void setBackground(Color color) {
    pane.setBackground(new Background(new BackgroundFill(color, null, null)));
  }

  /**
   * Converts model coordinates to screen coordinates.
   *
   * @param point the point to convert
   * @param max   the maximum value of the coordinate
   * @return the converted point
   */
  private double convertCoordinate(double point, double max) {
    double newPoint = point + MAX_POS;
    double ratio = newPoint / 2 * MAX_POS;
    return ratio * max;
  }

  /**
   * Moves the turtle to the specified location.
   *
   * @param x        the x coordinate of the destination
   * @param y        the y coordinate of the destination
   * @param duration the duration of the animation
   */
  public void moveTurtle(int id, double x, double y, double duration) {
    TurtleView turtle = turtleViewMap.get(id);
    double newX = convertCoordinate(x, pane.getPrefWidth());
    double newY = convertCoordinate(y, pane.getPrefHeight());
    //turtle.moveTurtleDuration(newX, newY, Duration.seconds(duration));
  }

  /**
   * Moves the turtle to the specified location at an instant. Does not animate.
   *
   * @param id          the ID of the turtle
   * @param coordinates the Coordinates for the turtle to move to
   */
  public void moveTurtle(int id, List<Double> coordinates) {
    xDisplacement = coordinates.get(0);
    yDisplacement = coordinates.get(1);
    TurtleView turtle = turtleViewMap.get(id);
//    double newX = convertCoordinate(x, pane.getPrefWidth());
//    double newY = convertCoordinate(y, pane.getPrefHeight());

    double newX = turtle.getX() + xDisplacement;
    double newY = turtle.getY() - yDisplacement;
    turtle.moveTurtle(newX, newY);
  }

  /**
   * Turns the turtle to the specified angle at an instant. Does not animate.
   *
   * @param angle the angle to turn to
   */
  public void rotateTurtle(int id, double angle) {
    TurtleView turtle = turtleViewMap.get(id);
    turtle.rotateTurtle(angle);
  }

  /**
   * Moves a turtle by the specified amount. Used by the public methods in the Turtle class.
   *
   * @param angle    the angle to turn to
   * @param duration the duration object of the animation
   */
  public void rotateTurtle(int id, double angle, double duration) {
    TurtleView turtle = turtleViewMap.get(id);
    turtle.rotateTurtle(angle);
  }

  /**
   * Sets the pen status of the turtle.
   *
   * @param penStatus the pen status
   */
  public void setPenStatus(int id, boolean penStatus) {
    TurtleView turtle = turtleViewMap.get(id);
    pane.getChildren().add(turtle.setPenStatus(penStatus));
  }

  /**
   * Sets the color of the current path.
   *
   * @param color the color to set
   */
  public void setCurrentPenColor(int id, Color color) {
    TurtleView turtle = turtleViewMap.get(id);
    turtle.setCurrentPenColor(color);
  }

  /**
   * Sets the color of a new path.
   *
   * @param color the color to set
   */
  public void setNewPenColor(int id, Color color) {
    TurtleView turtle = turtleViewMap.get(id);
    pane.getChildren().add(turtle.setNewPenColor(color));
  }

  /**
   * This function makes a turtle start moving (or doing what it has been asked to do)
   *
   * @param id    The ID of the turtle needed to be started
   * @param start A boolean deciding whether it should start or not
   */
  public void startTurtle(Boolean start) {

    ParallelTransition transition = new ParallelTransition();
    for(Integer turtle: turtleViewMap.keySet()){
      System.out.println(turtle);
      transition.getChildren().add(turtleViewMap.get(turtle).getPath());
    }
    transition.play();

  }

  public void clearTurtles(boolean clear){
    pane.getChildren().clear();
    for(Integer turtle: turtleViewMap.keySet()){
      turtleViewMap.get(turtle).getPath().getChildren().clear();
    }
  }

  /**
   * Clears all paths and creates a new path blank.
   */
  public void clearPaths(int id, boolean penStatus) {
    TurtleView turtle = turtleViewMap.get(id);
    turtle.clearPaths(penStatus);
  }

  /**
   * Sets the image of a turtle
   *
   * @param image The image the turtle will become
   */
  public void setTurtle(int id, Image image) {
    turtleViewMap.get(id).setTurtle(image);
  }

  /**
   * Returns all the turtles currently being held by the GraphicPane (hidden or not)
   *
   * @return The mapping of all the turtles
   */
  public Map<Integer, TurtleView> getTurtles() {
    return turtleViewMap;
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

}
