# Frontend Use Cases

### Ritvik Janamsetty, Jose Santillan, Kushagra Ghosh, Robert Cranston

### TEAM 4

### Converter
Converts model coordinates to JavaFX coordinates
```java
public class converter {
  // Parses a series of locations to move to from pure coordinates to JavaFX coordinates
  public void convertAndDisplay(double[][] movements, int time);
  // Converts the movements to JFX coordinates
  private double[][] convert(double[][] movements);
}
```
### TurtleView
Displays the turtle on the screen and controls the turtle
```java
public class TurtleView{
  // Moves the turtle to a new location
  public void move(double x, double y);
  // Rotates the turtle to a new angle
  public void rotate(double angle);
  // Sets the turtle to a new location and angle
  public void setLocation(double x, double y, double angle);
  // Changes the view of the turtle to new image
  public void changeImage(String imagepath);
}
```
### SidebarView
Displays the sidebar on the screen and controls the sidebar
```java
public class SidebarView{
  // Toggles the specified section in the sidebar
  public void toggleSection(String section);
  // Implements CSS styling
  public void setStyle(String style);
}
```