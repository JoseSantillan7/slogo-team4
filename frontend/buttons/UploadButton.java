package slogo.frontend.buttons;

import static slogo.controller.Controller.makeChooser;
import static slogo.frontend.panes.sidebar.Editor.NEW_LINE;

import java.io.File;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import slogo.controller.Message;
import slogo.frontend.panes.sidebar.Editor;

public class UploadButton extends ButtonSuper {

  private static final String FILE_EXTENSION = "*.txt";
  private final String TEXT = "upload";

  public UploadButton() {
    super();
  }

  public UploadButton(String s) {
    super(s);
  }

  @Override
  public void setDesign() {
    this.setText(TEXT);
  }

  @Override
  public void setID() {
    ID = UPLOAD_ID;
  }

  @Override
  public void setFunctionUpload(Window window, Editor toggle, ResourceBundle resources) {
    setOnAction(actionEvent -> {
      //implement functionality
      try {
        FileChooser fileChooser = makeChooser("Load File", FILE_EXTENSION);

        File file = fileChooser.showOpenDialog(window);

        Scanner scan = new Scanner(file);
        StringBuilder sb = new StringBuilder();

        while (scan.hasNext()) {
          sb.append(scan.nextLine()).append(NEW_LINE);
        }
        toggle.append(sb.toString());
        scan.close();


      } catch (Exception e) {
        //TODO Change this to fit a better catch statement

        Message errorMessage = new Message("UploadError", resources);
        errorMessage.showAndWait();

      }
    });
    isFuncSet = true;
  }

}
