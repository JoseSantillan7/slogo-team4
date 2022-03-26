package slogo.frontend.panes.sidebar.documentation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A class that represents a documentation entry for a command in the documentation pane.
 */
public class DocumentationParser {

  private static final int MAX_ENTRIES = 2;
  private static final String FILEPATH_BASE = "slogo/frontend/panes/sidebar/documentation/";
  private static final String SLASH = "/";
  private static final String NONE = "None";
  private static final char COLON = ':';
  private static final String DESCRIPTION = "Description";
  private static final String PARAMETERS = "Parameters";
  private static final String RETURNS = "Returns";
  private static final String DESCRIPTION_TAG = DESCRIPTION + COLON;
  private static final String PARAMETERS_TAG = PARAMETERS + COLON;
  private static final String RETURNS_TAG = RETURNS + COLON;
  private static final Map<String, Section> STATUS_TRACK = Map.of(DESCRIPTION_TAG,
      Section.DESCRIPTION, PARAMETERS_TAG, Section.PARAMETERS, RETURNS_TAG, Section.RETURNS);
  private final URL filepath;
  private final Map<String, String> aliases;
  private final Map<String, String> parameters;
  private String exprType;
  private String description;
  private String returns;

  /**
   * Creates a new DocumentationParser object, initializes required fields, and parses the
   * documentation file for easy access of the properties.
   *
   * @param filename the name of the documentation file
   */
  public DocumentationParser(String filename, String language) {
    ClassLoader loader = DocumentationParser.class.getClassLoader();
    filepath = loader.getResource(FILEPATH_BASE + language + SLASH + filename);
    aliases = new LinkedHashMap<>(MAX_ENTRIES);
    parameters = new LinkedHashMap<>(MAX_ENTRIES);
    parseDocumentation();
  }

  /**
   * Gets the expression type of the documentation entry.
   *
   * @return the expression type of the documentation entry
   */
  public String getDocExprType() {
    return exprType;
  }

  /**
   * Gets the aliases of the documentation entry
   *
   * @return the aliases of the documentation entry
   */
  public Map<String, String> getDocAliases() {
    return aliases;
  }

  /**
   * Gets the description of the documentation entry.
   *
   * @return the description of the documentation entry
   */
  public String getDocDescription() {
    return description;
  }

  /**
   * Gets the parameters of the documentation entry.
   *
   * @return the parameters of the documentation entry
   */
  public Map<String, String> getDocParameters() {
    return parameters;
  }

  /**
   * Gets the return description of the documentation entry.
   *
   * @return the return description of the documentation entry
   */
  public String getDocReturns() {
    return returns;
  }


  /**
   * Parses the documentation file and sets the properties of the DocumentationParser object.
   *
   * @throws IllegalArgumentException if the line is not formatted correctly or the section is not
   *                                  valid
   */
  private void parseDocumentation() throws IllegalArgumentException {
    File file = new File(filepath.getFile());
    Section currentSection = Section.ALIAS;
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty()) {
          continue;
        }
        currentSection = updateSection(line, currentSection);
        updateData(line, currentSection);
      }
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      System.out.println("File not found: " + filepath);
    } catch (IOException e) {
      System.out.println("Error reading file: " + filepath);
    }
  }

  /**
   * Updates the current section of the documentation file.
   *
   * @param line           the line of the documentation file
   * @param currentSection the current section of the documentation file
   * @return the updated current section of the documentation file
   * @throws IllegalArgumentException if the line is not formatted correctly or the section is not
   *                                  valid
   */
  private Section updateSection(String line, Section currentSection)
      throws IllegalArgumentException {
    char lastChar = line.charAt(line.length() - 1);
    if (lastChar == COLON) {
      return STATUS_TRACK.getOrDefault(line, Section.ALIAS);
    } else {
      return currentSection;
    }
  }

  /**
   * Determines what field to update and calls the appropriate method to update the field.
   *
   * @param line           the line of the documentation file
   * @param currentSection the current section of the documentation file
   * @throws IllegalArgumentException if the line is not formatted correctly or the section is not
   *                                  valid
   */
  private void updateData(String line, Section currentSection) throws IllegalArgumentException {
    char lastChar = line.charAt(line.length() - 1);
    if (lastChar != COLON) {
      switch (currentSection) {
        case ALIAS -> updateAlias(line);
        case DESCRIPTION -> description = line;
        case PARAMETERS -> updateParameters(line);
        case RETURNS -> returns = line;
        default -> throw new IllegalArgumentException("Invalid section: " + currentSection);
      }
    } else if (currentSection == Section.ALIAS) {
      exprType = line.substring(0, line.length() - 1);
    }
  }

  /**
   * Parses the line of the documentation file and updates the aliases field.
   *
   * @param line the line of the documentation file
   * @throws IllegalArgumentException if the line is not formatted correctly
   */
  private void updateAlias(String line) {
    String[] split = line.split("\\s+");
    if (split.length == 0) {
      throw new IllegalArgumentException("Invalid alias format: " + line);
    } else if (split.length == 1) {
      aliases.put(split[0], "");
    } else {
      String[] subslice = Arrays.copyOfRange(split, 1, split.length);
      aliases.put(split[0], String.join(" ", subslice));
    }
  }

  /**
   * Parses the line of the documentation file and updates the parameters field.
   *
   * @param line the line of the documentation file
   * @throws IllegalArgumentException if the line is not formatted correctly
   */
  private void updateParameters(String line) {
    if (line.equals(NONE)) {
      return;
    }
    String[] split = line.split("\\s+");
    if (split.length < 3) {
      throw new IllegalArgumentException("Invalid parameter format: " + line);
    }
    String[] subslice = Arrays.copyOfRange(split, 2, split.length);
    parameters.put(split[0], String.join(" ", subslice));
  }

  /**
   * Inner enum for the different types of documentation entries to keep track of current progress.
   */
  private enum Section {
    ALIAS, DESCRIPTION, PARAMETERS, RETURNS
  }

}


