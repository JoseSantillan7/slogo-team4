package slogo.model.parser;


import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;
import slogo.model.Turtle;

import java.util.*;
import java.util.function.BiFunction;



import slogo.model.commands.Command;

/**
 * Main class for parsing (uses Parser.java as a helper class for identifying the type of each part
 * of the program text)
 *
 * @author Kushagra Ghosh
 */
public class Parser {

  // regular expression representing one or more whitespace characters (space, tab, or newline)
  public static final String WHITESPACE = "\\s+";
  private static final String RESOURCES_FOR_PARAMETERS = Parser.class.getPackageName()+".ParametersForCommands";
  private static final int DEFAULT_PARAMS = 1;
  private ResourceBundle numOfParamsResources;
  private Classifier classifier;
  private List<Object> Lres = new ArrayList<>();
  //Pen and paths that will be passed into each command when the parser is done
  private Turtle myTurtle;
  private Map<String, List<Integer>> variables = new HashMap<>();
  private int numOfParamsinGroups = 0;
  private static final int GROUPMAXNUMOFCOMMANDS = 1000000;
  private static final int INDICATINGLISTHASSTARTED = 1000;

  /**
   * What will end up being the main constructor of the parser, which will pass the references of
   * the pen into each command so that each command can do its job with the pen reference.
   *
   * @param turtle
   * @param program
   * @param language
   */
  public Parser(Turtle turtle, String program, String language) {
    myTurtle = turtle;
    // set up the parser, which checks for matches in order given
    classifier = new Classifier();
    // these are more specific, so add them first to ensure they are checked first
    classifier.addPatterns(language);
    // general checks, added last
    classifier.addPatterns("Syntax");
    //Setting up resource bundle
    numOfParamsResources = ResourceBundle.getBundle(RESOURCES_FOR_PARAMETERS);
    // try against a program
    parseText(program);

  }

  /**
   * The main method for parsing the String program, populates the values list by the end of the program with the classes needed to run to execute the program
   * @param program String version of SLOGO programming text that the user types in the GUI.
   */
  private void parseText(String program) {
    //Initializing necessary variables for the parsing
    String programWithoutComments = removeCommentsFromCode(program);
    Stack<String> Scom = new Stack<>();
    Stack<Object> Sval = new Stack<>();
    int numberOfParamsToCheck = DEFAULT_PARAMS;
    String[] tokens = programWithoutComments.split(WHITESPACE);

    Lres = getResultListFromTokens(Scom, Sval, numberOfParamsToCheck, tokens);
    System.out.println("Result List: "+Lres);
  }

  /**
   * The primary helper method for parseText(), it takes in a String[] of tokens and parses the array and returns the resulting list of results
   * @param Scom
   * @param Sval
   * @param numberOfParamsToCheck
   * @param tokens
   * @return returns the resulting list of results after parsing as a List<Object>
   */
  private List<Object> getResultListFromTokens(Stack<String> Scom, Stack<Object> Sval, int numberOfParamsToCheck, String[] tokens) {
    //Looping through each token of the program String
    List<Object> resList = new ArrayList<>();
    for (int i = 0; i < tokens.length; i++) {
      numberOfParamsToCheck = addingToStackBasedOnTokenType(Scom, Sval, numberOfParamsToCheck, tokens, i);

      if(numberOfParamsToCheck==GROUPMAXNUMOFCOMMANDS){
        numOfParamsinGroups++;
      }
      else if(numberOfParamsToCheck>=INDICATINGLISTHASSTARTED){
        i = numberOfParamsToCheck%INDICATINGLISTHASSTARTED;
        numberOfParamsToCheck = numberOfParamsToCheck/INDICATINGLISTHASSTARTED;

      }

      //While the values stack contains enough parameters to create and add the command.
      while (Sval.size()>= numberOfParamsToCheck && Scom.size()>0){
        createCommandInValueStack(Scom, Sval);
        try{
          numberOfParamsToCheck = Integer.parseInt(numOfParamsResources.getString(classifier.getSymbol(Scom.peek())));
        } catch (EmptyStackException e){
          numberOfParamsToCheck =DEFAULT_PARAMS;
        }
        addToValuesListIfNoMoreCommands(Scom, Sval, resList);

        //System.out.println("Solving: "+ Scom + " "+ Sval);
      }
      addToValuesListIfNoMoreCommands(Scom, Sval, resList);
      //System.out.println("Computing: "+ Scom + " "+ Sval +"\n");

    }
    System.out.println("SVAL size: " + Sval.size() + " number of params: " + numberOfParamsToCheck);
    if(Sval.size()>= numberOfParamsToCheck){
      createCommandInValueStack(Scom, Sval);
    }
    addToValuesListIfNoMoreCommands(Scom, Sval, resList);
    return resList;
  }

  /**
   * Helper method that classifies each token and adds it to the appropriate stack
   * @param Scom Commands Stack
   * @param Sval Values Stack
   * @param numberOfParamsToCheck
   * @param tokens Each "word" of the program that is seperated by whitespace
   * @param indexOfTokens
   * @return the updated numberOfParamsToCheck based on what command was added to the stack if any
   */
  private int addingToStackBasedOnTokenType(Stack<String> Scom, Stack<Object> Sval, int numberOfParamsToCheck, String[] tokens, int indexOfTokens) {
    String type = Parser.getContext(tokens[indexOfTokens]);
    Map<String, BiFunction<String, Integer, Integer>> types = Map.of(
            "Constant", (tag, params) -> sortConstant(tag, params, Sval),
            "UserCommand", (tag, params) -> sortCommand(tag, params, Scom, Sval),
            "Variable", (tag, params) -> sortVariable(tag, params, Sval, Scom, tokens, indexOfTokens),
            "ListStart", (tag, params) -> sortListStart(tag, params, Sval, tokens, indexOfTokens),
            "GroupStart", (tag, params) -> sortGroupStart(tag, params),
            "GroupEnd", (tag, params) -> sortGroupEnd(tag, params, Scom, Sval) );
    numberOfParamsToCheck = types.get(type).apply(tokens[indexOfTokens], numberOfParamsToCheck);

    //System.out.println("Adding: "+Scom + " "+ Sval);
    return numberOfParamsToCheck;
  }

  public static String getContext(String tokens) {
    Classifier justSyntaxClassifier = new Classifier();
    justSyntaxClassifier.addPatterns("Syntax");

    String type = justSyntaxClassifier.getSymbol(tokens);
    return type;
  }


  private int sortConstant(String token, int numberOfParamsToCheck, Stack<Object> sval) {
    sval.push(Double.valueOf(token));
    return numberOfParamsToCheck;
  }
  private int sortCommand(String token, int numberOfParamsToCheck, Stack<String> scom, Stack<Object> sval) {
    scom.push(classifier.getSymbol(token));
    if(numOfParamsinGroups==0){
      numberOfParamsToCheck = sval.size()+Integer.parseInt(numOfParamsResources.getString(classifier.getSymbol(token)));
    }
    return numberOfParamsToCheck;
  }
  private int sortVariable(String token, int numberOfParamsToCheck, Stack<Object> Sval, Stack<String> Scom, String[] tokens, int indexOfTokens) {
    if(classifier.getSymbol(tokens[indexOfTokens-1]).equals("MakeVariable")){
      if(!variables.containsKey(token)){
        List<Integer> locationsOfVariable = new ArrayList<>();
        for(int i = indexOfTokens+1; i<tokens.length;i++){
          if(tokens[i].equals(token) && !classifier.getSymbol(tokens[i-1]).equals("MakeVariable")){
            tokens[i]=tokens[indexOfTokens+1];
            locationsOfVariable.add(i);
          }
        }
        variables.put(token, locationsOfVariable);
      }
      else {
        List<Integer> locationsOfVariable = variables.get(token);
        for(int location : locationsOfVariable){
          tokens[location]=tokens[indexOfTokens+1];
        }
      }
      Scom.pop();
    }
    else {
      Sval.push(0.0);
    }
    return numberOfParamsToCheck;
  }
  private int sortGroupStart(String token, int numberOfParamsToCheck) {
    return GROUPMAXNUMOFCOMMANDS;
  }
  private int sortGroupEnd(String token, int numberOfParamsToCheck, Stack<String> scom, Stack<Object> sval) {
    return numOfParamsinGroups-2;
  }

  private int sortListStart(String token, int numberOfParamsToCheck, Stack<Object> Sval, String[] tokens, int indexOfTokens) {
    Classifier justSyntaxClassifier = new Classifier();
    justSyntaxClassifier.addPatterns("Syntax");

    String[] listTokens = new String[0];
    int listEnding = 0;
    boolean hasCommands = false;
    for(int i = indexOfTokens+1; i<tokens.length;i++){
      if(justSyntaxClassifier.getSymbol(tokens[i]).equals("UserCommand")){
        hasCommands = true;
      }
      if(classifier.getSymbol(tokens[i]).equals("ListEnd")){
        listTokens = Arrays.copyOfRange(tokens, indexOfTokens+1, i);
        listEnding = i;
        break;
      }
    }
    System.out.println("ListEnding: "+listEnding+" listTokens: "+ Arrays.stream(listTokens).toList());
    if(hasCommands && listTokens.length>0){
      Sval.push(getResultListFromTokens(new Stack<String>(), new Stack<Object>(), DEFAULT_PARAMS, listTokens));

    }
    else {
      Sval.push(convertStringListTodoubleList(Arrays.stream(listTokens).toList(), Double::parseDouble));
      System.out.println("SVAL: " + Sval);
    }
    return numberOfParamsToCheck*INDICATINGLISTHASSTARTED+listEnding;
  }

  //Method to convert List of string to Doubles from : https://stackoverflow.com/questions/59174133/java-convert-listliststring-into-listlistdouble
  public static <T, U> List<U> convertStringListTodoubleList(List<T> listOfString, Function<T, U> function)
  {
    return listOfString.stream()
        .map(function)
        .collect(Collectors.toList());
  }

  /**
   * Public method that gets the resulting List of result classes to execute (the result of the parsing)
   * @return
   */
  public List<Object> getLres() {
    System.out.println("LRES: " + Lres);
    return Lres;
  }

  /**
   * When the command stack has size 0 and there is only 1 value left in the values stack, the remaining value is removed and added to the result list where it could be executed later.
   * @param Scom Commands Stack
   * @param Sval Values Stack
   * @return the updated result list
   */
  private List<Object> addToValuesListIfNoMoreCommands(Stack<String> Scom, Stack<Object> Sval, List<Object> resList) {
    if (Scom.size() == 0 && Sval.size() == 1) {
      Object value = Sval.pop();
      resList.add(value);
    }
    return resList;
  }

  /**
   * Takes the top command in Scom and the required number of parameter values and adds that to an array.
   * Then, it creates a new command and pushes that command class and pushes that command into Sval.
   * @param scom Commands Stack
   * @param sval Values Stack
   */
  private void createCommandInValueStack(Stack<String> scom, Stack<Object> sval) {
    System.out.println("in command");
    List<Object> commandAndValue = new ArrayList<>();
    String command = scom.pop();
    commandAndValue.add(command);
    //System.out.println("numOfParamsinGroups now????: "+numOfParamsinGroups);
    if(numOfParamsinGroups==0){
      getAllParamValuesFromStack(sval, commandAndValue, Integer.parseInt(numOfParamsResources.getString(command)));
    }
    else{
      getAllParamValuesFromStack(sval, commandAndValue, numOfParamsinGroups-2);
      numOfParamsinGroups = 0;
    }
    sval.push(Command.evaluateNewCommand(commandAndValue));
  }

  /**
   * Helper method during refactoring that eliminates repeated code and adds all params to List<Object> commandAndValue from Stack<Object> sval
   * @param sval
   * @param commandAndValue
   * @param paramLimit
   */
  private void getAllParamValuesFromStack(Stack<Object> sval, List<Object> commandAndValue, int paramLimit) {
    for (int i = 0; i < paramLimit; i++) {
      Object value = sval.pop();
      commandAndValue.add(value);
    }
  }


  /**
   * Given the classifier and some text, prints results of just seperating each token and printing out its classification
   * @param program
   * @return
   */
  public String parseTextAndPrint(String program) {
    String result = "";
    String programWithoutComments = removeCommentsFromCode(program);
    for (String token : programWithoutComments.split(WHITESPACE)) {
      if (token.equals("")) {
        continue;
      }
      result += String.format("%s:%s ", token, classifier.getSymbol(token));
    }
    return result;
  }

  /**
   * remove comments from program code because they do not need to be parsed and executed (but
   * doesn't use regex)
   *
   * @param program
   * @return
   */
  private String removeCommentsFromCode(String program) {
    String newProgram = program;
    System.out.println(newProgram);
    while (newProgram.contains("#")) {
      int commentLocation = newProgram.indexOf("#");
      if (newProgram.substring(commentLocation).contains("\n")) {
        newProgram = newProgram.replace(
            newProgram.substring(program.indexOf("#"), newProgram.indexOf("\n") + 1), "");
      } else {
        newProgram = newProgram.replace(newProgram.substring(newProgram.indexOf("#")), "");
      }
    }
    return newProgram;
  }

}
