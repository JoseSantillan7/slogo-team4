package slogo.controller;

import java.util.ResourceBundle;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class Message extends Dialog {

  private final String TITLE = "Title";
  private final String TEXT = "Text";

  public Message(String title, String text) {
    setTitle(title);
    setContentText(text);
    createButton();
  }
  public Message(String property, ResourceBundle resources) {

    setTitle(resources.getString(property + TITLE));
    setContentText(resources.getString(property + TEXT));
    createButton();
  }
  private void createButton() {
    ButtonType button = new ButtonType("Ok", ButtonData.OK_DONE);
    getDialogPane().getButtonTypes().add(button);

  }

}
