package slogo.model.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.SingleTurtle;
import slogo.model.Turtle;
import slogo.model.TurtleManager;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @author Kushagra Ghosh
 */
class ParserTest {
    private Parser myParser;
    private Classifier classifier;
    private String program;
    private String language;

    @BeforeEach
    void setUp() {
        language = "English";
    }

    @Test
    void testParseTextAndPrint() {
        program = "fd sum sum sum sum 10 20 30 5 5 fd fd 50 # comment";
        myParser = new Parser(new TurtleManager( null ,null, null, null, null), program, language);
        assertEquals("fd:Forward sum:Sum sum:Sum sum:Sum sum:Sum 10:Constant 20:Constant 30:Constant 5:Constant 5:Constant fd:Forward fd:Forward 50:Constant ", myParser.parseTextAndPrint(program));
    }

    @Test
    void testParseForSimpleProgram(){
        program = "fd 50";
        myParser = new Parser(new TurtleManager( null, null, null, null, null), program, language);
        assertTrue(myParser.getLres().toString().contains("[slogo.model.commands.turtle_commands.Forward"));
    }

    @Test
    void testParseForDrawingTriangle(){
        program="# draw a triangle\n" +
                "fd 50\n" +
                "rt 120\n" +
                "fd 100\n" +
                "rt 120\n" +
                "fd 100\n" +
                "rt 120\n" +
                "fd 50";
        myParser = new Parser(new TurtleManager( null, null, null, null, null), program, language);
        assertTrue(myParser.getLres().toString().startsWith("[slogo.model.commands.turtle_commands.Forward")&&myParser.getLres().toString().contains("slogo.model.commands.turtle_commands.Right"));
    }

    @Test
    void testParseForVariables(){
        program="make :x 10 set :x 25 fd fd :x rt :x # comment";
        myParser = new Parser(new TurtleManager( null, null, null, null, null), program, language);
        assertTrue(myParser.getLres().toString().startsWith("[10.0, 25.0"));      //To test if the make/set variable command returns the expected value
        assertTrue(myParser.getLres().toString().contains("slogo.model.commands.turtle_commands.Right") && myParser.getLres().toString().contains("slogo.model.commands.turtle_commands.Forward") );
    }

    @Test
    void testParseForCommandsInCom1(){
        program="fd sum 10 sum 10 rt sum 5 5";
        myParser = new Parser(new TurtleManager( null, null, null, null, null), program, language);
        assertTrue(myParser.getLres().toString().startsWith("[slogo.model.commands.turtle_commands.Forward"));
    }

    @Test
    void testParseForCommandsInCom2(){
        program="rt sum 10 sum 10 sum 10 sum 20 20";
        myParser = new Parser(new TurtleManager( null, null, null, null, null), program, language);
        assertTrue(myParser.getLres().toString().startsWith("[slogo.model.commands.turtle_commands.Right"));
    }

    @Test
    void testParseForExtendedSyntax(){
        program="fd ( sum 10 20 30 40 )";
        myParser = new Parser(new TurtleManager( null, null, null, null, null), program, language);
        assertTrue(myParser.getLres().toString().startsWith("[slogo.model.commands.turtle_commands.Forward"));
    }

}