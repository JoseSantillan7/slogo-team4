package slogo.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import slogo.frontend.buttons.ButtonSuper;

public class Controller {

  private ResourceBundle myResources;
  private static final String RESOURCE_EXTENSION_PATH = "slogo/languages/";
  private static final String NEW_LINE = "\n";
  private final Stage myStage;

  private final ViewController myView;
  private final ModelController myModel;
  private static final Double MOVE = 30.0;

  public Controller(Stage stage, Map<String, String> preferences) {
    String language = preferences.get("language");

    myResources = ResourceBundle.getBundle(RESOURCE_EXTENSION_PATH + language);


    myView = new ViewController(myResources);
    myModel = new ModelController(myView.clearTurtle(), myView.createTurtle(), myView.onMove(), myView.onRotate(), myView.onPenChange(), myView.startTurtle());



    myStage = stage;

    initButtons();
  }

  public Controller(Stage stage, String language) {
    myResources = ResourceBundle.getBundle(RESOURCE_EXTENSION_PATH + language);

    myView = new ViewController(myResources);
    myModel = new ModelController(myView.clearTurtle(), myView.createTurtle(), myView.onMove(), myView.onRotate(), myView.onPenChange(), myView.startTurtle());

    myStage = stage;

    initButtons();
  }

  /**
   * This method makes a FileChooser so that a user can open a dialog box to choose a file
   *
   * @param title Title of the dialog box
   * @return The FileChooser
   */
  public static FileChooser makeChooser(String title, String fileExtension) {
    FileChooser file = new FileChooser();
    file.setTitle(title);
    file.setInitialDirectory(new File(System.getProperty("user.dir")));
    file.getExtensionFilters().setAll(new ExtensionFilter("Text File", fileExtension));

    return file;
  }

  private void initButtons() {
    List<ButtonSuper> buttons = myView.getButtons();

    for (ButtonSuper button : buttons) {
      setFunctionality(button);
    }

  }

  private void setFunctionality(ButtonSuper button) {
    int ID = button.getID();

    if (button.isFuncSet()) {
      return;
    }

    switch (ID) {
      case ButtonSuper.START_ID -> setStartFunctionality(button);

      case ButtonSuper.TERMINATE_ID -> setTerminateFunctionality(button);

      case ButtonSuper.PAUSE_ID -> setPauseFunctionality(button);

      case ButtonSuper.DEBUG_ID -> setDebugFunctionality(button);

      case ButtonSuper.UPLOAD_ID -> setUploadFunctionality(button);

      case ButtonSuper.DOWNLOAD_ID -> setDownloadFunctionality(button);

      case ButtonSuper.UPLOAD_TURTLE_ID -> setUploadTurtleFunctionality(button);

      case ButtonSuper.FORWARD -> setForward(button);

      case ButtonSuper.BACK -> setBack(button);

      case ButtonSuper.RIGHT -> setRight(button);

      case ButtonSuper.LEFT -> setLeft(button);
    }
  }
  private Integer getValue() {
    try {
      return (Integer) myView.getTurtleCombo().getValue();
    } catch (Exception ignored) {

    }
    return 1;
  }
  private void setForward(ButtonSuper button) {
    button.setOnAction(event -> {
      Integer id = getValue();
      myModel.start(String.format("tell [ %d ] fd %f", id, MOVE));
    });
  }
  private void setBack(ButtonSuper button) {
    button.setOnAction(event -> {
      Integer id = getValue();
      myModel.start(String.format("tell [ %d ] bk %f", id, MOVE));
    });
  }
  private void setRight(ButtonSuper button) {
    button.setOnAction(event -> {
      Integer id = getValue();
      myModel.start(String.format("tell [ %d ] right %f", id, MOVE));
    });
  }
  private void setLeft(ButtonSuper button) {
    button.setOnAction(event -> {
      Integer id = getValue();
      myModel.start(String.format("tell [ %d ] left %f", id, MOVE));
    });
  }
  private void setUploadTurtleFunctionality(ButtonSuper button) {
    button.setUploadImage(myStage, myView.getGraphicPane(), myResources);
  }

  private void setDownloadFunctionality(ButtonSuper button) {
    button.setFunction(myStage, myView.getEditor(), myResources);
  }

  private void setUploadFunctionality(ButtonSuper button) {
    button.setFunctionUpload(myStage, myView.getEditor(), myResources);
  }

  private void setTerminalFunctionality(ButtonSuper button) {
    button.setOnAction(event -> {

    });

  }

  private void setPauseFunctionality(ButtonSuper button) {
    button.setOnAction(actionEvent -> {
      //implement functionality
    });
  }

  private void setTerminateFunctionality(ButtonSuper button) {
    button.setOnAction(actionEvent -> {
      //implement functionality
    });
  }

  private void setStartFunctionality(ButtonSuper button) {
    button.setOnAction(actionEvent -> {

      String commands = myView.getEditorText();
      //parse and start commands
      //myModel.start(commands);
      //step through each line and do animation
      try {
        myModel.start(commands);

        List<String> commandList = List.of(commands.split("\n"));

        myView.addToHistory(commandList);

      } catch (Exception e) {
        myView.addToTerminal(e.getMessage());
      }
    });
  }

  private void setDebugFunctionality(ButtonSuper button) {
    button.setOnAction(actionEvent -> {
      //implement functionality
    });
  }

  /**
   * Starts the view in ViewManager
   */
  public void startView(Stage stage) {
    myView.startView(stage);
  }


}
