package slogo.frontend.Windows;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

abstract public class NewWindow extends Application {

  public static final int SIZE = 600;
  public static final int PADDING = 20;

  private Stage myStage;
  private BorderPane myRoot;
  private Scene myDisplay;

  public NewWindow(Stage stage) {
    myStage = stage;
    myRoot = new BorderPane();
    myDisplay = new Scene(myRoot, SIZE, SIZE);

    initComponents(myStage, myRoot, myDisplay);
  }

  @Override
  public void start(Stage stage) throws Exception {
    myStage.setScene(myDisplay);
    myStage.show();
  }

  abstract public void initComponents(Stage stage, BorderPane root, Scene display);

}
