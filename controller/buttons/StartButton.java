package slogo.controller.buttons;

import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Window;
import slogo.frontend.buttons.ButtonSuper;
import slogo.frontend.panes.sidebar.Editor;

public class StartButton extends ButtonSuper {

  private final String FILE_PATH = "/images/runbutton.png";

  public StartButton() {
    super();
  }

  @Override
  public void setDesign() {

    ImageView image = new ImageView(new Image(getClass().getResourceAsStream(FILE_PATH)));
    image.setFitHeight(20);

    image.setFitWidth(20);
    this.setGraphic(image);
    //this.setStyle("-fx-background-color: clear");
  }

  @Override
  public void setID() {
    ID = START_ID;
  }
}
