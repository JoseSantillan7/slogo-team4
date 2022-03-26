package slogo.frontend.panes.sidebar.styling.color;

import java.util.Objects;
import javafx.scene.paint.Color;

/**
 * Creates a ColorEntry used to create store data till a graphic is set. Allows for sorting by HSB
 * values.
 *
 * @param name  the name of the color
 * @param color the color to be stored
 * @author Ritvik Janamsetty
 */
public record ColorEntry(String name, Color color) implements Comparable<ColorEntry> {

  /**
   * Compares this object with the specified object for order. Returns a negative integer, zero, or
   * a positive integer as this object is less than, equal to, or greater than the specified
   * object.
   * <p>ColorEntry objects are compared by their color's hue, saturation, and brightness. This
   * approach was taken from <a href="https://www.alanzucconi.com/2015/09/30/colour-sorting/>this
   * article on different ways to sort colors</a>.
   */
  @Override
  public int compareTo(ColorEntry o) {
    //Sort color by HSB value
    int compare = Double.compare(color.getHue(), o.color.getHue());
    if (compare != 0) {
      return compare;
    }
    compare = Double.compare(color.getSaturation(), o.color.getSaturation());
    if (compare != 0) {
      return compare;
    }
    return Double.compare(color.getBrightness(), o.color.getBrightness());
  }


  /**
   * Indicates whether some other object is "equal to" this one.
   *
   * @param o the reference object with which to compare.
   * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ColorEntry that)) {
      return false;
    }
    return Objects.equals(color, that.color);
  }

  /**
   * Returns a hash code value for the object.
   *
   * @return a hash code value for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(color);
  }
}
