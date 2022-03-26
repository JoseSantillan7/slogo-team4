package slogo.frontend.panes.sidebar.styling.turtle;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import slogo.frontend.exceptions.TurtlePropertyNotFoundException;

/**
 * Holder for static methods for parsing turtle styling. Contains methods for creating an ImageView,
 * Rectangle, Circle, Ellipse, Polygon, RegularPolygon, and SVGPath.
 *
 * @author Ritvik Janamsetty
 */
public record TurtleReflector(String fileName, String methodName) {

  private static final String PATH = "path";
  private static final String WIDTH = "width";
  private static final String HEIGHT = "height";
  private static final double TAU = Math.PI * 2;
  private static final String SIDES = "sides";
  private static final String STYLING_PACKAGE = TurtleReflector.class.getPackageName();
  private static final String OPTIONS_DIRECTORY = ".options.";
  private static final String DEFAULT_DIRECTORY = STYLING_PACKAGE + OPTIONS_DIRECTORY;
  private static final String CLASS_NAME = TurtleReflector.class.getSimpleName();
  private static final double RADIUS = 15;
  private static final String ROTATE = "rotate";
  private static final String NAME = "name";
  private static final String IMAGEPATH_BASE = "slogo/frontend/panes/sidebar/styling/turtle/images/";

  /**
   * Creates a turtle entry based on a properties file using reflection.
   *
   * @param fileName   the name of the properties file
   * @param methodName the name of the method to invoke
   * @return the turtle entry
   */
  public static TurtleEntry invokeReflector(String fileName, String methodName) {
    return new TurtleReflector(fileName, methodName).invoke();
  }

  /**
   * Creates a turtle entry based on an ImageView from a resource bundle. Required values are the
   * {@code "name"} and {@code "path"}.
   *
   * @param fileName the resource bundle containing the image properties
   * @return the turtle entry
   */
  public static TurtleEntry makeImage(String fileName) {
    Map<String, String> param = convertResourceBundleToMap(fileName);
    String imagePath = getRequiredValue(PATH, param);
    String name = getRequiredValue(NAME, param);
    Image image = new Image(IMAGEPATH_BASE + imagePath);
    ImageView imageView = new ImageView(image);
    param.computeIfPresent(ROTATE, (k, v) -> {
      double rotate = Double.parseDouble(v);
      imageView.setRotate(rotate);
      return v;
    });
    return new TurtleEntry(name, imageView);
  }

  /**
   * Creates a turtle entry based on a Rectangle. Required values are the {@code "width"}, {@code
   * "height"}, and {@code "name"}.
   *
   * @param fileName the resource bundle containing the rectangle properties
   * @return the turtle entry
   */
  public static TurtleEntry makeRectangle(String fileName) throws NumberFormatException {
    Map<String, String> param = convertResourceBundleToMap(fileName);
    double width = Double.parseDouble(getRequiredValue(WIDTH, param));
    double height = Double.parseDouble(getRequiredValue(HEIGHT, param));
    Shape rect = new Rectangle(width, height);
    return makeTurtleEntryFromShape(rect, param);
  }

  /**
   * Creates a turtle entry based on a Circle. Required value is just the {@code "name"}.
   *
   * @param fileName the resource bundle containing the circle properties
   * @return the turtle entry
   */
  public static TurtleEntry makeCircle(String fileName) throws NumberFormatException {
    Map<String, String> param = convertResourceBundleToMap(fileName);
    Shape circle = new Circle(RADIUS);
    return makeTurtleEntryFromShape(circle, param);
  }

  /**
   * Creates a turtle entry based on a Polygon with the number of sides. Required values are the
   * {@code "sides"} and {@code "name"}.
   * <p> Code sourced from <a href=https://stackoverflow.com/questions/49550428/on-javafx-is-there-any-way-to-draw-a-regular-polygon-without-knowing-the-coordi>this
   * Stackoverflow post</a>
   *
   * @param fileName the resource bundle containing the polygon properties
   * @return the turtle entry
   */
  public static TurtleEntry makePolygon(String fileName) throws NumberFormatException {
    Map<String, String> param = convertResourceBundleToMap(fileName);
    int sides = Integer.parseInt(getRequiredValue(SIDES, param));
    Polygon polygon = new Polygon();
    double angleStep = TAU / sides;
    double angle = 0;
    for (int i = 0; i < sides; i++, angle += angleStep) {
      polygon.getPoints().addAll(Math.sin(angle) * RADIUS, // X Coordinate
          Math.cos(angle) * RADIUS // Y Coordinate
      );
    }
    return makeTurtleEntryFromShape(polygon, param);
  }

  /**
   * Creates a turtle entry based on a SVGPath. Required values are the {@code "path"} and {@code
   * "name"}.
   *
   * @param fileName the resource bundle containing the SVGPath properties
   * @return the turtle entry
   */
  public static TurtleEntry makeSVG(String fileName) {
    Map<String, String> param = convertResourceBundleToMap(fileName);
    String path = getRequiredValue(PATH, param);
    SVGPath svg = new SVGPath();
    svg.setContent(path);
    return makeTurtleEntryFromShape(svg, param);
  }

  /**
   * Creates a turtle entry based on a Shape from a resource bundle.
   *
   * @param shape the shape
   * @param param the map containing the shape properties
   * @return the turtle entry
   */
  public static TurtleEntry makeTurtleEntryFromShape(Shape shape, Map<String, String> param)
      throws TurtlePropertyNotFoundException {
    String name = getRequiredValue(NAME, param);
    return new TurtleEntry(name, shape);
  }

  /**
   * Converts a resource bundle into a map of strings to turtle entries.
   *
   * @param fileName the name of the file
   * @return the map
   */
  private static Map<String, String> convertResourceBundleToMap(String fileName)
      throws MissingResourceException {
    ResourceBundle resource = ResourceBundle.getBundle(DEFAULT_DIRECTORY + fileName);
    Map<String, String> map = new HashMap<>();
    resource.keySet().forEach(key -> map.put(key, resource.getString(key)));
    return map;
  }

  /**
   * Concise function to get a required property from a map and throw an exception if it is not
   * found.
   *
   * @param key the key
   * @param map the map
   * @return the value
   * @throws TurtlePropertyNotFoundException if the property is not found
   */
  private static String getRequiredValue(String key, Map<String, String> map)
      throws TurtlePropertyNotFoundException {
    String value = map.getOrDefault(key, "");
    if (value.isEmpty()) {
      throw new TurtlePropertyNotFoundException(CLASS_NAME, key);
    }
    return value;
  }

  /**
   * Uses reflection to create a turtle entry based on a shape from a resource bundle.
   *
   * @return the turtle entry
   */
  public TurtleEntry invoke() {
    try {
      Method m = this.getClass().getDeclaredMethod(methodName, String.class);
      return (TurtleEntry) m.invoke(this, fileName);
    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | MissingResourceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

}
