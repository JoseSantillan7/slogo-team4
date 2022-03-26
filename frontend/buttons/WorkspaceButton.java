package slogo.frontend.buttons;

import javafx.geometry.Pos;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import slogo.frontend.Windows.NewWindowSetup;
import slogo.frontend.SLogoView;

public class WorkspaceButton extends ButtonSuper {

  public WorkspaceButton(SLogoView view) {
    super();
    setFunctionality(view);
    setAlignment(Pos.TOP_RIGHT);
  }

  public WorkspaceButton(String s, SLogoView view) {
    super(s);
    setFunctionality(view);
    setAlignment(Pos.TOP_RIGHT);
  }

  @Override
  public void setDesign() {

  }
  private void setFunctionality(SLogoView view) {
    setOnAction(event -> {

      NewWindowSetup newWindow = new NewWindowSetup(view);
      try {
        newWindow.start(new Stage());
      } catch (Exception e) {
        e.printStackTrace();
      }

    });
    Tooltip tooltip = new Tooltip();

    tooltip.setText("Opens a New Workspace");
    tooltip.setStyle("-fx-font: normal bold 14 Langdon; "
        + "-fx-base: white; "
        + "-fx-text-fill: orange;");

    setTooltip(tooltip);
  }

}
