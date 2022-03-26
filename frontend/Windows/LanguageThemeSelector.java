package slogo.frontend.Windows;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import slogo.controller.Controller;
import slogo.controller.Message;

public class LanguageThemeSelector extends NewWindow {

  private ComboBox<String> myLanguageSelector;
  private ComboBox<String> myThemeSelector;
  private String myLanguage;
  private Stage myStage;

  public LanguageThemeSelector(Stage stage) {
    super(stage);
  }

  @Override
  public void initComponents(Stage stage, BorderPane root, Scene display) {
    myStage = stage;
    setComboBoxes();

    VBox box = new VBox(initText(), groupCombo());
    box.setAlignment(Pos.CENTER);
    root.setCenter(box);

    initButton(root);
    initContainer(root);
  }

  private VBox initContainer(BorderPane root) {
    VBox box = new VBox(initText(),groupCombo());
    box.setAlignment(Pos.CENTER);
    root.setCenter(box);
    return box;
  }

  protected HBox groupCombo() {
    HBox box = new HBox();
    box.getChildren().addAll(myLanguageSelector, myThemeSelector);
    box.setAlignment(Pos.CENTER);
    box.setPadding(new Insets(10));
    box.setSpacing(10);
    return box;
  }

  protected void setComboBoxes() {
    List<String> themes = new ArrayList<>(List.of("Dummy1", "Dummy2"));
    List<String> languages = new ArrayList<>(
        List.of("Chinese", "English", "French", "German", "Italian", "Russian", "Spanish", "Urud"));

    myLanguageSelector = initComboBox(languages);
    myThemeSelector = initComboBox(themes);
    myLanguageSelector.setValue("Languages");
    myThemeSelector.setValue("Themes");
  }

  protected Text initText() {
    return new Text("Please Select a Language and a Theme");
  }

  private ComboBox<String> initComboBox(List<String> items) {
    ComboBox<String> combo = new ComboBox<>();
    combo.getItems().addAll(items);
    return combo;
  }

  protected Button initButton(BorderPane myRoot) {
    Button selectButton = new Button("Select");
    selectButton.setOnAction(event -> {
      if (!myLanguageSelector.getValue().equals("Languages") && !myThemeSelector.getValue()
          .equals("Themes")) {
        myLanguage = myLanguageSelector.getValue();

        myStage.close();
        myStage = new Stage();

        Controller myController = new Controller(myStage, myLanguage);
        myController.startView(myStage);
      } else {

        String title = "No Language/Theme Selected";
        String text = "Please select a language/theme!";
        Message message = new Message(title, text);
        message.showAndWait();
      }
    });
    BorderPane.setAlignment(selectButton, Pos.BOTTOM_RIGHT);
    myRoot.setBottom(selectButton);
    return selectButton;
  }
}
