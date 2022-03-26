package slogo.frontend.panes.sidebar.styling.color;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import slogo.frontend.util.FrontendComponent;
import slogo.frontend.util.NodeComboBox;

/**
 * A ComboBox that displays a list of colors.
 *
 * @author Ritvik Janamsetty
 */
public class ColorSelector implements FrontendComponent {

  private static final int MAX_COLOR_VALUE = 255;
  private static final int MIN_COLOR_VALUE = 0;
  private static final String STYLING_PACKAGE = ColorSelector.class.getPackageName();
  private static final String FILENAME = ".DefaultColors";
  private final ResourceBundle defaultColors;
  private final Set<ColorEntry> colorEntries;
  private final List<ColorEntry> themeColors;
  private final ComboBox<ColorEntry> selector;

  /**
   * Creates a ColorSelector with the default colors specified in the resource file.
   */
  public ColorSelector(double width) {
    defaultColors = ResourceBundle.getBundle(STYLING_PACKAGE + FILENAME);
    selector = new NodeComboBox<>(ColorCell::makeEntry);
    colorEntries = new TreeSet<>();
    defaultColors.keySet()
        .forEach(
            key -> colorEntries.add(new ColorEntry(key, parseColor(defaultColors.getString(key)))));
    selector.getItems().addAll(colorEntries);
    selector.setPrefWidth(width);
    themeColors = new ArrayList<>();
  }

  /**
   * Gets the selected color entry.
   *
   * @return the selected color entry
   */
  public ColorEntry getSelected() {
    return selector.getValue();
  }

  /**
   * Gets all added theme colors export in properties file format.
   *
   * @return all added theme colors in properties file format
   */
  public List<ColorEntry> getThemeColors() {
    return themeColors;
  }

  /**
   * Converts a string representation of a color to a Color object.
   *
   * @param color the string representation of a color as r,g,b from 0 to 255
   * @return the Color object
   */
  private Color parseColor(String color) {
    String[] rgb = color.split(",");
    int r = normalizeColor(Integer.parseInt(rgb[0]));
    int g = normalizeColor(Integer.parseInt(rgb[1]));
    int b = normalizeColor(Integer.parseInt(rgb[2]));
    return Color.rgb(r, g, b);
  }

  /**
   * Adds a color to the color map from its name and rgb values.
   *
   * @param name the name of the color
   * @param r    the red value of the color from 0 to 255
   * @param g    the green value of the color from 0 to 255
   * @param b    the blue value of the color from 0 to 255
   */
  public void addColor(String name, int r, int g, int b) {
    Color color = Color.rgb(normalizeColor(r), normalizeColor(g), normalizeColor(b));
    addColor(name, color);
  }

  /**
   * Adds a color to the color map from its name and hex value.
   *
   * @param name  the name of the color
   * @param color the hex value of the color
   */
  public void addColor(String name, String color) {
    addColor(name, parseColor(color));
  }

  /**
   * Adds a color to the color map from its name and Color object.
   *
   * @param name  the name of the color
   * @param color the Color object
   */
  public void addColor(String name, Color color) {
    ColorEntry entry = new ColorEntry(name, color);
    themeColors.add(entry);
    selector.getItems().add(entry);
  }


  /**
   * Returns the ComboBox used to select a color.
   *
   * @return the ComboBox used to select a color
   */
  @Override
  public Node getNode() {
    return selector;
  }

  /**
   * Normalizes the color values to be between 0 and 255.
   *
   * @param value the value to normalize
   * @return the normalized value
   */
  private int normalizeColor(int value) {
    if (value < MIN_COLOR_VALUE) {
      return MIN_COLOR_VALUE;
    }
    return Math.min(value, MAX_COLOR_VALUE);
  }

}
