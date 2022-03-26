package slogo.frontend.buttons;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import javax.imageio.ImageIO;
import slogo.controller.Message;
import slogo.frontend.panes.display.GraphicPane;

public class UploadTurtleButton extends ButtonSuper {

  private static final int TURTLE_SIZE = 30;

  public UploadTurtleButton() {
    super();
  }

  public static FileChooser makeChooser(String title) {
    FileChooser file = new FileChooser();
    file.setTitle(title);
    file.setInitialDirectory(new File(System.getProperty("user.dir")));
    file.getExtensionFilters()
        .setAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

    return file;
  }

  @Override
  public void setDesign() {
    setText("UploadTurtleImage");
  }

  public void setID() {
    ID = ButtonSuper.UPLOAD_TURTLE_ID;

  }

  public void setUploadImage(Window window, GraphicPane graphic, ResourceBundle resources) {
    setOnAction(event -> {
      try {

        FileChooser filechooser = makeChooser("Load Image");
        File file = filechooser.showOpenDialog(window);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream bais;

        BufferedImage img = ImageIO.read(file);
        ImageIO.write(img, "png", baos);

        baos.close();

        bais = new ByteArrayInputStream(baos.toByteArray());

        TextInputDialog input = createDialog();
        input.showAndWait();

        int id = Integer.parseInt(input.getEditor().getText());

        graphic.setTurtle(id, new Image(bais, TURTLE_SIZE, TURTLE_SIZE, true, true));
      } catch (Exception e) {
        Message message = new Message("Image Upload Failure",
            "Oops! Something went wrong, please try again!");
        message.showAndWait();
      }
    });
  }

  private TextInputDialog createDialog() {
    return new TextInputDialog("ENTER ID OF TURTLE YOU WANT TO CHANGE");
  }
}
