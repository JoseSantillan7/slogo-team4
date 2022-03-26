package slogo.frontend.panes.sidebar.styling.weights;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import slogo.frontend.util.FrontendComponent;
import slogo.frontend.util.NodeComboBox;

/**
 * A ComboBox that displays a list of line weights.
 *
 * @author Ritvik Janamsetty
 */
public class LineWeightSelector implements FrontendComponent {

  public static final String PT = "pt";
  private static final String STYLING_PACKAGE = LineWeightSelector.class.getPackageName();
  private static final String FILENAME = ".DefaultWeights";
  private final ResourceBundle defaultWeights;
  private final Set<LineWeightEntry> weightEntries;
  private final List<LineWeightEntry> themeWeights;
  private final ComboBox<LineWeightEntry> selector;

  /**
   * Creates a LineWeightSelector with the default line weights specified in the resource file.
   */
  public LineWeightSelector(double width) {
    defaultWeights = ResourceBundle.getBundle(STYLING_PACKAGE + FILENAME);
    selector = new NodeComboBox<>(LineWeightCell::makeEntry);
    weightEntries = new TreeSet<>();
    defaultWeights.keySet().forEach(key -> weightEntries.add(
        new LineWeightEntry(Double.parseDouble(key), defaultWeights.getString(key))));
    selector.getItems().addAll(weightEntries);
    selector.setPrefWidth(width);
    themeWeights = new ArrayList<>();
  }

  /**
   * Returns the ComboBox used to select a line weight.
   *
   * @return the ComboBox used to select a line weight
   */
  @Override
  public Node getNode() {
    return selector;
  }

  /**
   * Gets the selected line weight.
   *
   * @return the selected line weight
   */
  public double getValue() {
    return selector.getValue().weight();
  }

  /**
   * Adds a weight to the selector with its value and name.
   *
   * @param weight the weight to add
   * @param name   the name of the weight
   */
  public void addWeight(double weight, String name) {
    LineWeightEntry entry = new LineWeightEntry(weight, name);
    themeWeights.add(entry);
    selector.getItems().add(entry);
  }

  /**
   * Adds a weight to the selector with just its value.
   *
   * @param weight the weight to add
   */
  public void addWeight(double weight) {
    addWeight(weight, weight + PT);
  }

  /**
   * Gets a list of theme weights.
   *
   * @return a list of theme weights
   */
  public List<LineWeightEntry> getThemeWeights() {
    return themeWeights;
  }
}
