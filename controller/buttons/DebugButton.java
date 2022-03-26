package slogo.controller.buttons;

import slogo.frontend.buttons.ButtonSuper;

public class DebugButton extends ButtonSuper {

  private final String TEXT = "Debug";

  public DebugButton() {
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
