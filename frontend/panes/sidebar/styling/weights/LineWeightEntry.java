package slogo.frontend.panes.sidebar.styling.weights;

import java.util.Objects;

/**
 * Creates a LineWeightEntry used to create store data till a graphic is set. Allows for sorting by
 * weight.
 *
 * @author Ritvik Janamsetty
 */
public record LineWeightEntry(double weight, String name) implements Comparable<LineWeightEntry> {

  /**
   * Compares this object with the specified object for order.  Returns a negative integer, zero, or
   * a positive integer as this object is less than, equal to, or greater than the specified
   * object.
   */
  @Override
  public int compareTo(LineWeightEntry o) {
    return Double.compare(weight, o.weight);
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
    if (!(o instanceof LineWeightEntry that)) {
      return false;
    }
    return Double.compare(that.weight, weight) == 0;
  }

  /**
   * Returns a hash code value for the object.
   *
   * @return a hash code value for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(weight);
  }
}
