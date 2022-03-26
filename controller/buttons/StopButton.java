package slogo.controller.buttons;

import slogo.frontend.buttons.ButtonSuper;

public class StopButton extends ButtonSuper {

  private final String TEXT = "Stop";

  public StopButton(){
    super();
  }
  @Override
  public void setDesign() {
    this.setText(TEXT);
  }

  @Override
  public void setID() {

  }
}
