package slogo.frontend.panes.sidebar.documentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import slogo.frontend.panes.TogglePane;
import slogo.frontend.util.FrontendComponent;

public class DocumentationEntry extends TogglePane implements Comparable<DocumentationEntry>,
    FrontendComponent {

  private static final String DESCRIPTION = "Description";
  private static final String PARAMETERS = "Parameters";
  private static final String RETURNS = "Returns";
  private static final String SHOW = "Show";
  private static final String HIDE = "Hide";
  private final VBox details;
  private final VBox container;
  private final FlowPane header;
  private final String name;
  private final String file;
  private final String language;
  private final List<Label> labelList;
  private Button toggle;
  private Map<String, String> aliases;
  private Map<String, String> parameters;
  private String exprType;
  private String description;
  private String returns;
  private boolean toggleState = COLLAPSE;

  public DocumentationEntry(String name, String file, String language) {
    this.name = name;
    this.file = file;
    this.language = language;
    labelList = new ArrayList<>();
    container = new VBox();
    parseData();
    header = createHeader();
    details = createDetails();
    container.getChildren().addAll(header, details);
    toggleView(COLLAPSE);
  }

  /**
   * Creates the header for the documentation entry.
   *
   * @return the header for the documentation entry.
   */
  private FlowPane createHeader() {
    FlowPane header = new FlowPane();
    header.setHgap(5);
    Label title = createLabel(name);
    header.getChildren().add(title);
    if (!name.equals(file)) {
      Label fileName = createLabel(file);
      header.getChildren().add(fileName);
    }
    toggle = new Button(SHOW);
    toggle.setOnAction(e -> toggleAction());
    header.getChildren().add(toggle);
    return header;
  }

  /**
   * Creates the container for the documentation entry.
   *
   * @return the container for the documentation entry.
   */
  private VBox createDetails() {
    VBox details = new VBox();
    details.getChildren().add(createMapTextDisplay(exprType, aliases));
    details.getChildren().add(createSimpleTextDisplay(DESCRIPTION, description));
    if (parameters.size() > 0) {
      details.getChildren().add(createMapTextDisplay(PARAMETERS, parameters));
    }
    details.getChildren().add(createSimpleTextDisplay(RETURNS, returns));
    return details;
  }

  /**
   * Creates a simple text display for the documentation entry.
   *
   * @param header the header for the documentation entry.
   * @param text   the text to be displayed in the description.
   * @return the text display for the documentation entry.
   */
  private VBox createSimpleTextDisplay(String header, String text) {
    VBox descriptionPane = createSection(header);
    Label descriptionLabel = createLabel(text);
    descriptionPane.getChildren().add(descriptionLabel);
    return descriptionPane;
  }


  /**
   * Creates a text display for the documentation entry from a map.
   *
   * @param header the header for the documentation entry.
   * @param map    the map containing the text to be displayed.
   * @return the text display for the documentation entry.
   */
  private VBox createMapTextDisplay(String header, Map<String, String> map) {
    VBox descriptionPane = createSection(header);
    for (Entry<String, String> entry : map.entrySet()) {
      HBox entryBox = new HBox();
      Label key = createLabel(entry.getKey());
      Label value = createLabel(entry.getValue());
      key.setWrapText(true);
      value.setWrapText(true);
      entryBox.getChildren().addAll(key, value);
      descriptionPane.getChildren().add(entryBox);
    }
    return descriptionPane;
  }

  /**
   * Creates a section header for the documentation entry.
   *
   * @param sectionName the name of the section.
   * @return the vbox containing the section header.
   */
  private VBox createSection(String sectionName) {
    VBox section = new VBox();
    Label sectionLabel = createLabel(sectionName);
    section.getChildren().add(sectionLabel);
    return section;
  }

  /**
   * Parses the date from the documentation parser.
   */
  private void parseData() {
    DocumentationParser parser = new DocumentationParser(file, language);
    exprType = parser.getDocExprType();
    description = parser.getDocDescription();
    returns = parser.getDocReturns();
    aliases = parser.getDocAliases();
    parameters = parser.getDocParameters();
  }

  /**
   * Determines whether the documentation entry is expanded or collapsed and toggles the view.
   */
  private void toggleAction() {
    if (toggleState) {
      toggleView(COLLAPSE);
    } else {
      toggleView(EXPAND);
    }
  }

  /**
   * Creates a label with it being wrapped.
   *
   * @param text the text to be displayed.
   * @return the label with it being wrapped.
   */
  private Label createLabel(String text) {
    Label label = new Label(text);
    label.setWrapText(true);
    label.setTextAlignment(TextAlignment.LEFT);
    labelList.add(label);
    return label;
  }

  /**
   * Toggles the visibility of the pane to new state. {@code true} expands the pane, {@code false}
   * shrinks it to the default size.
   *
   * @param newState the new state of the pane.
   */
  @Override
  public void toggleView(boolean newState) {
    toggleVisibility(details, newState);
    toggle.setText(newState ? HIDE : SHOW);
    toggleState = newState;
  }

  /**
   * Compares this object with the specified object for order. Sorts first by Returns a negative
   * integer, zero, or a positive integer as this object is less than, equal to, or greater than the
   * specified object.
   *
   * @param o the object to be compared.
   * @return a negative integer, zero, or a positive integer as this object is less than, equal to,
   * or greater than the specified object.
   * @throws NullPointerException if the specified object is null
   * @throws ClassCastException   if the specified object's type prevents it from being compared to
   *                              this object.
   */
  @Override
  public int compareTo(DocumentationEntry o) {
    int compareExpr = exprType == null ? 0 : exprType.compareTo(o.exprType);
    return compareExpr == 0 ? name.compareTo(o.name) : compareExpr;
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
    if (!(o instanceof DocumentationEntry that)) {
      return false;
    }
    return Objects.equals(aliases, that.aliases) && Objects.equals(parameters,
        that.parameters) && Objects.equals(exprType, that.exprType)
        && Objects.equals(description, that.description) && Objects.equals(
        returns, that.returns) && Objects.equals(name, that.name)
        && Objects.equals(file, that.file);
  }

  /**
   * Returns a hash code value for the object. This method is supported for the benefit of hash
   * tables such as those provided by HashMap.
   *
   * @return a hash code value for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(aliases, parameters, exprType, description, returns, name, file);
  }

  /**
   * Returns the node that represents this component.
   *
   * @return the node that represents this component.
   */
  @Override
  public Node getNode() {
    return container;
  }
}
