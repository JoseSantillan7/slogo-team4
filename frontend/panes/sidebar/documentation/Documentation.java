package slogo.frontend.panes.sidebar.documentation;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import slogo.frontend.panes.TogglePane;
import slogo.frontend.util.FrontendComponent;

/**
 * Controls the editor pane
 *
 * @author Jose Santillan
 * @author Ritvik Janamsetty
 */
public class Documentation extends TogglePane implements FrontendComponent {

  private static final String COLOR = "Yellow";
  private static final String DOC_FILES_PACKAGE =
      Documentation.class.getPackageName() + ".Filename";
  private static final String LANGUAGES_PACKAGE = slogo.Main.class.getPackageName() + ".languages.";
  private static final String LANGUAGE = "English";
  private final ScrollPane container;
  private Map<String, String> docFilesMap;
  private Map<String, String> languageMap;
  private Set<DocumentationEntry> entries;


  /**
   * Initializes the documentation pane. Contains a list of documentation entries. The header will
   * be in the given language, but the documentation itself will be in English.
   */
  public Documentation() {
    loadResources();
    getEntries();
    VBox box = new VBox();
    entries.forEach(entry -> box.getChildren().add(entry.getNode()));
    container = createScrollPane(box, COLOR);
  }

  /**
   * Gets the entries for the documentation pane.
   */
  private void getEntries() {
    entries = new TreeSet<>();
    docFilesMap.forEach(
        (key, value) -> entries.add(new DocumentationEntry(languageMap.get(key), value, LANGUAGE)));
  }

  /**
   * Loads the resources for the documentation pane.
   */
  private void loadResources() {
    ResourceBundle docFiles = ResourceBundle.getBundle(DOC_FILES_PACKAGE);
    docFilesMap = docFiles.keySet().stream()
        .collect(Collectors.toMap(key -> key, docFiles::getString));
    ResourceBundle languageFiles = ResourceBundle.getBundle(LANGUAGES_PACKAGE + LANGUAGE);
    languageMap = languageFiles.keySet().stream().collect(
        Collectors.toMap(key -> key, key -> languageFiles.getString(key).split("[^a-z]")[0]));
  }

  /**
   * Toggles the visibility of the pane to new state.
   *
   * @param newState the new state of the pane.
   */
  @Override
  public void toggleView(boolean newState) {
    toggleVisibility(container, newState);
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
