package slogo.frontend.Windows;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PreferenceSelector extends NewWindow {

  private VBox buttonHolder;
  private Stage myStage;
  private BorderPane myRoot;
  private Scene myDisplay;

  public PreferenceSelector(Stage stage) {
    super(stage);
  }

  @Override
  public void initComponents(Stage stage, BorderPane root, Scene display) {
    myStage = stage;
    myRoot = root;
    myDisplay = display;

    buttonHolder = new VBox(PADDING);
    buttonHolder.setAlignment(Pos.CENTER);
    buttonHolder.getChildren().addAll(makeButton(), noPreference());
    root.setCenter(buttonHolder);
  }

  public Button makeButton() {
    PreferenceButton preferenceButton = new PreferenceButton("Select Default Preferences", myStage);
    return preferenceButton;
  }

  public Button noPreference() {
    Button button = new Button("No Preference");
    button.setOnAction(event -> {
      LanguageThemeSelector language = new LanguageThemeSelector(new Stage());
      try {
        language.start(new Stage());
      } catch (Exception e) {
        e.printStackTrace();
      }
      myStage.close();
    });
    return button;
  }
}
