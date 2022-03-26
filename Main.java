package slogo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.stage.Stage;
import slogo.frontend.Windows.LanguageThemeSelector;
import slogo.frontend.Windows.PreferenceSelector;


/**
 * Feel free to completely change this code or delete it entirely.
 */
public class Main extends Application {

  private static final String LANGUAGE_RESOURCE_PATH = "/slogo/languages/";
  private static final String EXAMPLE_PROGRAMS_PATH = "/examples";

  private List<Main> windows;
  /**
   * Start of the program.
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Get command in a given language.
   */
  public String getCommand(String language, String command) {
    ResourceBundle resources = ResourceBundle.getBundle(LANGUAGE_RESOURCE_PATH + language);
    return resources.getString(command);
  }

  /**
   * Get first line of given example program.
   */
  public String getExampleProgram(String category, String example) {
    // regular expression representing one or more whitespace characters (space, tab, or newline)
    final String NEWLINE = "\\n+";

    List<String> lines = readFile(
        String.format("%s/%s/%s.slogo", EXAMPLE_PROGRAMS_PATH, category, example), NEWLINE);
    return lines.get(0).trim();
  }

  /**
   * A method to test (and a joke :).
   */
  public double getVersion() {
    return 0.001;
  }

  // this code is dense, hard to read, and throws exceptions so better to wrap in method
  private List<String> readFile(String filename, String delimiter) {
    try {
      String path = getClass().getResource(filename).toExternalForm();
      String data = new String(Files.readAllBytes(Paths.get(new URI(path))));
      return Arrays.asList(data.split(delimiter));
    } catch (URISyntaxException | IOException | NullPointerException e) {
      // NOT ideal way to handle exception, but this is just a simple test program
      System.out.println("ERROR: Unable to read input file " + e.getMessage());
      return Collections.emptyList();
    }
  }

  @Override
  public void start(Stage stage) throws Exception{
    PreferenceSelector preferenceSelector = new PreferenceSelector(stage);
    preferenceSelector.start(stage);

  }

}
