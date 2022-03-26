package slogo.controller.buttons;

import static slogo.controller.Controller.makeChooser;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import slogo.controller.Message;
import slogo.frontend.buttons.ButtonSuper;
import slogo.frontend.panes.Editable;

public class DownloadButton extends ButtonSuper {

  private static final String FILE_EXTENSION = "*.txt";
  private static final String TEXT = "download";

  public DownloadButton() {
    super();
  }

  public DownloadButton(String s) {
    super(s);
  }

  @Override
  public void setDesign() {
    this.setText(TEXT);
  }

  @Override
  public void setID() {
    ID = DOWNLOAD_ID;
  }

  public void setFunction(Window window, Editable toggle, ResourceBundle resources) {
    setOnAction(actionEvent -> {
      //implement functionality
      try {
        //This body tries to save to the same file if a file has been uploaded
        FileChooser fileChooser = makeChooser("Save", FILE_EXTENSION);

        File newFile = fileChooser.showSaveDialog(window);

        String saveText = toggle.getText();
        saveTextToFile(saveText, newFile);

      } catch (NullPointerException e) {
        Message errorMessage = new Message("DownloadError", resources);
        errorMessage.showAndWait();
      }

    });
    isFuncSet = true;
  }

  private void saveTextToFile(String content, File file) {
    try {
      PrintWriter writer;
      writer = new PrintWriter(file);
      writer.println(content);
      writer.close();

    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
