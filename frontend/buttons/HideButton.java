package slogo.frontend.buttons;

public class HideButton extends ButtonSuper {

  private static final String NAME = "Hide";

  public HideButton() {
    super();
  }

  @Override
  public void setDesign() {
    this.setText(NAME);
  }

  @Override
  public void setID() {

  }
}
