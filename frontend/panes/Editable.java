package slogo.frontend.panes;

public interface Editable {

  /**
   * Gets the text in the text field in the pane and returns it.
   *
   * @return the text in the text field
   */
  String getText();

  /**
   * Sets the text in the text field in the pane to the given text.
   *
   * @param s the text to set the text field to
   */
  void append(String s);

}
