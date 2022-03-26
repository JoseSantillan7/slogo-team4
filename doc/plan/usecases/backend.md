# Backend Use Cases

### Ritvik Janamsetty, Jose Santillan, Kushagra Ghosh, Robert Cranston

### TEAM 4

### Converts pixel value recieved for forward command to model coordinates
```java
public class forwardCommand {
  // Finds the next position (model coordinates) of the turtle based on the pixel value passed in 
  public void createNextPosition(double pixelValue);
  // returns the double value of the distance turtle moved in pixel (a double return is required for all commands)
  public double returnValue() throws Exception;
}
```

### Executes what a sum command means in the model and returns the sum value or an Exception for some data not being provided for the command to work 
```java
public class sumCommand {
  // Finds the mathematical sum, which is the job of this command
  public void findSum(double expr1, double expr2);
  // returns the double value of the sum of the values of expr1 and expr2 (a double return is required for all commands)
  public double returnValue() throws Exception;
}
```

### Executes what a NOT command means in the model and returns the Not value or an Exception if some data is not being provided for the command to work 
```java
public class notCommand {
  // Finds the opposite value (1 if 0, or 0 if 1), which is the job of this command
  public void findNotValue(double test);
  // returns the double value of the not comand (a double return is required for all commands)
  public double returnValue() throws Exception;
}
```

### Get the text of commands entered by user 

```java
public class model {
  public String getCommands() {
    //getCommands will return a string containing the full cleaned text entered by the user.
    myCommands = controller.getCommands();
  }
}
```

###Creating a new instance of the parser and feeding in inputs
```java
public class model {

  public List<Callback <e>> parseCommands() {
    Parser parser = new Parser(myCommands);
    return parser.getFunctions();
  }
}
```

###Parser class to parse commands from user
```java
public class Parser{
  //initialized with string of commands from the controller
  public Parser(String commands);
  
  public void parseCommands();
  //will return the parsed commands to the model
  public List<Callback <e>> getFunctions();
}
```
