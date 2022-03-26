package slogo.frontend.exceptions;

import java.util.MissingResourceException;

public class TurtlePropertyNotFoundException extends MissingResourceException {

  private static final String DEFAULT_MESSAGE = "Turtle Reflection Property Not Found";

  /**
   * Constructs a TurtlePropertyNotFoundException with the specified information. A detail message
   * is a String that describes this particular exception. Extends MissingResourceException and
   * differs in that this class is used primarily when there is a
   *
   * @param className the name of the resource class
   * @param key       the key for the missing resource.
   */
  public TurtlePropertyNotFoundException(String className, String key) {
    super(DEFAULT_MESSAGE, className, key);
  }
}
