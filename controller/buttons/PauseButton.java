package slogo.controller.buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import slogo.frontend.buttons.ButtonSuper;

public class PauseButton extends ButtonSuper {

  private final String TEXT = "Pause";

  public PauseButton() {
    super();
  }

  @Override
  public void setDesign() {
    this.setText(TEXT);

    this.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {

      }
    });
  }

  @Override
  public void setID() {

  }


}
