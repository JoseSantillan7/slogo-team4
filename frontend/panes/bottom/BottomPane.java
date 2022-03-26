package slogo.frontend.panes.bottom;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import slogo.controller.buttons.DownloadButton;
import slogo.frontend.buttons.ButtonSuper;
import slogo.frontend.buttons.HideButton;
import slogo.frontend.buttons.HistoryButton;
import slogo.frontend.buttons.TerminalButton;
import slogo.frontend.buttons.TurtleFactButton;
import slogo.frontend.buttons.UploadButton;
import slogo.frontend.util.FrontendComponent;

public class BottomPane implements FrontendComponent {

  private static final boolean OFF = false;
  private static final boolean ON = true;

  private VBox pane;

  private History myHistoryPane;
  private Terminal myTerminalPane;
  private TurtleFact myTurtleFacts;
  private ResourceBundle myResources;

  public BottomPane(Scene scene, double prefHeight, ResourceBundle resources) {
    myResources = resources;
    initComponents(scene);
    togglePanes(OFF, ON, OFF);

    pane.setPrefHeight(prefHeight);
  }

  private void initComponents(Scene scene) {
    pane = new VBox();
    VBox.setVgrow(pane, Priority.ALWAYS);

    FlowPane buttonHolder = initButtons(scene);
    buttonHolder.setHgap(5);
    myHistoryPane = new History();
    myTerminalPane = new Terminal();
    myTurtleFacts = new TurtleFact();

    pane.getChildren().addAll(buttonHolder, myHistoryPane.getNode(), myTerminalPane.getNode(),
        myTurtleFacts.getNode());
  }

  /**
   * Toggles the visibility of the history pane and the terminal pane.
   *
   * @param historyVisibility  the visibility of the history pane
   * @param terminalVisibility the visibility of the terminal pane
   */
  private void togglePanes(boolean historyVisibility, boolean terminalVisibility, boolean turtleFactsVisibility) {
    myHistoryPane.toggleView(historyVisibility);
    myTerminalPane.toggleView(terminalVisibility);
    myTurtleFacts.toggleView(turtleFactsVisibility);
  }

  private FlowPane initButtons(Scene scene) {
    //Instantiate the buttons
    FlowPane buttonHolder = new FlowPane();
    ButtonSuper terminalButton = new TerminalButton();
    ButtonSuper historyButton = new HistoryButton();
    ButtonSuper hideButton = new HideButton();
    ButtonSuper uploadHistoryButton = new UploadButton("UploadHistory");
    ButtonSuper downloadHistoryButton = new DownloadButton("DownloadHistory");
    ButtonSuper turtleFacts = new TurtleFactButton("Turtle Facts");
    //Add functionality to the buttons

    terminalButton.setOnAction(event -> togglePanes(OFF, ON, OFF));
    historyButton.setOnAction(event -> togglePanes(ON, OFF, OFF));
    hideButton.setOnAction(event -> togglePanes(OFF, OFF, OFF));
    turtleFacts.setOnAction(event -> togglePanes(OFF, OFF, ON));

    uploadHistoryButton.setFunction(scene.getWindow(), myHistoryPane, myResources);
    downloadHistoryButton.setFunction(scene.getWindow(), myHistoryPane, myResources);

    //Add buttons to the pane
    buttonHolder.getChildren().addAll(terminalButton, historyButton, turtleFacts, uploadHistoryButton, downloadHistoryButton, hideButton);
    return buttonHolder;
  }

  /**
   * Gets the main container for this class, i.e, it returns the Pane that contains all the Nodes created in this class
   * @return pane
   */
  public VBox getPane() {
    return pane;
  }

  /**
   * Gets the HistoryHBox node from this pane
   * @return myHistoryPane
   */
  public History getHistory() {
    return myHistoryPane;
  }


  /**
   * Returns the node that represents this component.
   *
   * @return the node that represents this component.
   */
  @Override
  public Node getNode() {
    return pane;
  }

  /**
   * Gets the TurtleFact node from this pane
   * @return myTurtleFacts
   */
  public TurtleFact getTurtleFact() {
    return myTurtleFacts;
  }

  public void addToTerminal(String message) {
    myTerminalPane.append(message);
  }
}
