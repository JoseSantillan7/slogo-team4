package slogo.frontend.Windows;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import slogo.Main;
import slogo.controller.Controller;
import slogo.controller.Message;
import slogo.frontend.SLogoView;


public class NewWindowSetup extends NewWindow {


  private SLogoView view;
  private HBox buttonHolder;
  private Stage myStage;
  private BorderPane myRoot;
  private Scene myDisplay;

  public NewWindowSetup(SLogoView view) {
    super(new Stage());
    this.view = view;
  }

  @Override
  public void initComponents(Stage stage, BorderPane root, Scene display) {
    myStage = stage;
    myRoot = root;
    myDisplay = display;

    buttonHolder = new HBox(PADDING);
    buttonHolder.setAlignment(Pos.CENTER);
    buttonHolder.getChildren().addAll(makeScratch(), new PreferenceButton("New Preferences", myStage), makePreferences());
    root.setCenter(buttonHolder);
  }

  private Button makeScratch() {
    Button button = new Button("New Window");
    button.setOnAction(event -> {
      Main newMain = new Main();
      try {
        newMain.start(new Stage());
      } catch (Exception e) {
        e.printStackTrace();
      }
      myStage.close();
    });
    return button;
  }

  private Button makePreferences() {
    Button button = new Button("New Window with Default Preferences");
    button.setOnAction(event -> {
      Stage newStage = new Stage();
      try {
        Controller newWindow = new Controller(newStage, view.getPreferences());
        newWindow.startView(newStage);
        myStage.close();
      } catch (Exception e) {
        Message noDefault = new Message("No Default Preferences", "There are not Default Preferences on File, please upload a preference file in order to use this feature");
        noDefault.showAndWait();
      }
    });
    return button;
  }
}
