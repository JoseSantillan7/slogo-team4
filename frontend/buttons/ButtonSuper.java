package slogo.frontend.buttons;

import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.stage.Window;
import slogo.frontend.panes.Editable;
import slogo.frontend.panes.display.GraphicPane;
import slogo.frontend.panes.sidebar.Editor;


public abstract class ButtonSuper extends Button {

  public static final int DEFAULT_ID = -1;
  public static final int START_ID = 1;
  public static final int PAUSE_ID = 2;
  public static final int TERMINATE_ID = 3;
  public static final int DEBUG_ID = 4;
  public static final int UPLOAD_ID = 5;
  public static final int DOWNLOAD_ID = 6;
  public static final int UPLOAD_TURTLE_ID = 7;
  public static final int TERMINAL_ID = 8;
  public static final int FORWARD = 9;
  public static final int BACK = 10;
  public static final int RIGHT = 11;
  public static final int LEFT = 12;


  protected boolean isFuncSet;
  protected int ID;

  public ButtonSuper() {
    super();
    setDesign();
    setID();
    isFuncSet = false;
  }

  public ButtonSuper(String s) {
    super(s);
    setID();
    isFuncSet = false;
  }

  abstract public void setDesign();

  public void setID() {
    ID = DEFAULT_ID;
  }

  /**
   * Getter method for the ButtonSuper ID
   *
   * @returns the ID of this current object
   */
  public int getID() {
    return ID;
  }

  public boolean isFuncSet() {
    return isFuncSet;
  }

  public void setFunction(Window window, Editable toggle, ResourceBundle resources) {}

  public void setUploadImage(Window window, GraphicPane graphicPane, ResourceBundle resources) {}

  public void setFunctionUpload(Window window, Editor toggle, ResourceBundle resources) {}

}
