package slogo.frontend.Windows;

import static slogo.controller.Controller.makeChooser;

import java.io.File;
import java.util.Map;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import slogo.controller.Controller;
import slogo.controller.Message;
import slogo.frontend.Windows.XML.PreferenceXMLParser;

public class PreferenceButton extends Button {

  private static final String FILE_EXTENSION = "*.xml";
  private Stage myStage;

  public PreferenceButton(String s, Stage stage) {
    super(s);
    setFunction(myStage);
    myStage = stage;
  }

  private void setFunction(Window window) {
    setOnAction(event -> {
      try {
        FileChooser fileChooser = makeChooser("Choose a Preference File", FILE_EXTENSION);
        File preferences = fileChooser.showOpenDialog(window);
        Map<String, String> info = new PreferenceXMLParser().getInformation(preferences);

        Stage newStage = new Stage();
        Controller myController = new Controller(newStage, info);
        myController.startView(newStage);

        myStage.close();
      }catch(Exception e) {
       Message message = new Message("Invalid XML", "Invalid or No XML File Selected, please try again");
       message.showAndWait();
      }

    });
  }
}
