package slogo.frontend.panes.display;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import slogo.controller.buttons.DebugButton;
import slogo.controller.buttons.DownloadButton;
import slogo.controller.buttons.PauseButton;
import slogo.controller.buttons.StartButton;
import slogo.controller.buttons.StopButton;
import slogo.frontend.SLogoView;
import slogo.frontend.Turtleable;
import slogo.frontend.buttons.BackClick;
import slogo.frontend.buttons.ButtonSuper;
import slogo.frontend.buttons.ForwardClick;
import slogo.frontend.buttons.TurnLeft;
import slogo.frontend.buttons.TurnRight;
import slogo.frontend.buttons.UploadButton;
import slogo.frontend.buttons.UploadTurtleButton;
import slogo.frontend.buttons.WorkspaceButton;
import slogo.frontend.util.FrontendComponent;

public class ButtonPane implements FrontendComponent {

  private TurtleCombo myCombo;
  private FlowPane flow;
  private ResourceBundle myResources;

  public ButtonPane(Turtleable facts, Turtleable styler, SLogoView view, ResourceBundle resources) {
    flow = new FlowPane();
    myResources = resources;
    initButtons(facts, styler, view);
  }

  private FlowPane initButtons(Turtleable facts, Turtleable styler, SLogoView view) {

    ButtonSuper start = new StartButton();
    PauseButton pause = new PauseButton();
    StopButton stop = new StopButton();
    DebugButton debug = new DebugButton();

    UploadButton upload = new UploadButton("UploadEditor");
    DownloadButton download = new DownloadButton("DownloadEditor");
    UploadTurtleButton turtle = new UploadTurtleButton();
    ForwardClick fd = new ForwardClick("ForwardClick");
    BackClick bk = new BackClick("BackClick");
    TurnRight right = new TurnRight("TurnRight");
    TurnLeft left = new TurnLeft("TurnLeft");

    myCombo = new TurtleCombo(facts, styler);
    WorkspaceButton workspaceButton = new WorkspaceButton("+", view);
    flow.setHgap(5);
    flow.getChildren()
        .addAll(start, pause, stop, debug, upload, download, turtle, myCombo, fd, bk, right, left, workspaceButton);

    return flow;
  }

  /**
   * Gets the main container that holds all the Nodes created in this class
   *
   * @return A FlowPane
   */
  public FlowPane getPane() {
    return flow;
  }

  /**
   * Gets the TurtleCombo Node form the wide selection of nodes
   *
   * @return TurtleCombo
   */
  public TurtleCombo getTurtleCombo() {
    return myCombo;
  }

  /**
   * Returns the node that represents this component.
   *
   * @return the node that represents this component.
   */
  @Override
  public Node getNode() {
    return flow;
  }
}
