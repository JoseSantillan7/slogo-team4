package slogo.model.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @author Kushagra Ghosh
 */
class ClassifierTest {
    private Classifier myClassifier;

    @BeforeEach
    void setUp() {
        myClassifier = new Classifier();
        // these are more specific, so add them first to ensure they are checked first
        myClassifier.addPatterns("English");
        // general checks, added last
        myClassifier.addPatterns("Syntax");
    }

    /**
     * The following tests are custom tests that I wrote for testing the getSymbol() method (which tests the rest of the Classifier class code as well)
     */

    @Test
    void testConstant() {
        assertEquals("Constant", myClassifier.getSymbol("50"));
        assertEquals("Constant", myClassifier.getSymbol("-1.3"));
    }
    @Test
    void testVariable() {
        assertEquals("Variable", myClassifier.getSymbol(":distance"));
        assertEquals("Variable", myClassifier.getSymbol(":side"));
    }
    @Test
    void testCommand() {
        assertEquals("Forward", myClassifier.getSymbol("forward"));
        assertEquals("Forward", myClassifier.getSymbol("fd"));
        assertEquals("UserCommand", myClassifier.getSymbol("SiDeWaYs"));
    }
    @Test
    void testList() {
        assertEquals("ListStart", myClassifier.getSymbol("["));
        assertEquals("ListEnd", myClassifier.getSymbol("]"));
    }
    @Test
    void testComment() {
        assertEquals("Comment", myClassifier.getSymbol("# a comment"));
        assertEquals("Comment", myClassifier.getSymbol("#fd 50\n"));
    }


    /**
     * The following tests are from the spike_slogo project that Professor Duvall provided us, specifically from src/test/java/regex/ProgramParserTest.java
     * I (Kushagra) am using this test as additional tests to see if the my classifier works for all the conditions that the spike_slogo parser works for
     * @param text
     */

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {
            "",
            "   ",
            "\t\t",
            "\n",
    })
    void testEmptyExpressions (String text) {
        assertEquals(Classifier.NO_MATCH, myClassifier.getSymbol(text));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "# fd",
            " # forward ",
            " #forward ",
            " # # # ",
            "\t# fd",
            "\n# fd",
    })
    void testCommentVariations (String text) {
        assertEquals("Comment",myClassifier.getSymbol(text));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "foo #",
            "foo # bar",
    })
    void testIncorrectCommentVariations (String text) {
        assertEquals(Classifier.NO_MATCH,myClassifier.getSymbol(text));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "0",
            "1",
            "12",
            "1234567890",
            "0.0",
            "1.2",
            "1.",
            "123.45",
            "-10",
            "-1.0",
    })
    void testNumberVariations (String text) {
        assertEquals("Constant",myClassifier.getSymbol(text));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            ".1",      // must have a digit on left of dot
            "+1",      // no explicit positive numbers
            "--1",     // no stacking negatives
            "1.0.0",
    })
    void testIncorrectNumberVariations (String text) {
        assertEquals(Classifier.NO_MATCH,myClassifier.getSymbol(text));
    }

    @ParameterizedTest
    @CsvSource({
            "ListStart,[",
            "ListEnd,]",
    })
    void testGroupExpressions (String expected, String text) {
        assertEquals(expected,myClassifier.getSymbol(text));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "[]",
            "{",
            "}",
    })
    void testIncorrectGroupExpressions (String text) {
        assertEquals(Classifier.NO_MATCH,myClassifier.getSymbol(text));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            ":var",
            ":VAR",
            ":Var ",
    })
    void testVariableNameVariations (String text) {
        assertEquals("Variable",myClassifier.getSymbol(text));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            ":",           // must have at least one letter
            ":var?",       // question mark is only allowed in command names
            ":var_var",    // no underscores allowed
            "::var",       // only one colon allowed
            ":var:",
    })
    void testIncorrectVariableNameVariations (String text) {
        assertEquals(Classifier.NO_MATCH,myClassifier.getSymbol(text));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "fd",
            "FD",
            "forward ",
            "Forward",
            "FORward",
            "FORWARD",
    })
    void testCommandNameVariations (String text) {
        assertEquals("Forward",myClassifier.getSymbol(text));
    }

    @ParameterizedTest
    @CsvSource({
            "Or,or",
            "For,for",
            "Forward,forward",
    })
    void testOverlappingCommandNames (String expected, String text) {
        assertEquals(expected,myClassifier.getSymbol(text));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "userdefined",
            "UserDefined",
            "userdefined?",
            "_userdefined_",
            "user_defined",
            "user__defined",
            // NOT matched as FORWARD, but correct syntax for user-defined command
            "forwardd",
            "fwd",
    })
    void testUserCommandVariations (String text) {
        assertEquals("UserCommand",myClassifier.getSymbol(text));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "userdefined??",     // only one question mark allowed
            "?userdefined",      // question mark allowed only at end
            "user1defined",      // no numbers allowed
            "userdefined$",      // no special characters
            "userdefined:",      // colons only allowed in variable name
    })
    void testIncorrectUserCommandVariations (String text) {
        assertEquals(Classifier.NO_MATCH,myClassifier.getSymbol(text));
    }

    @ParameterizedTest
    @CsvSource({
            "Sum,+",
            "Sum,sum",
            "Difference,-",
            "Product,*",
            "Quotient,/",
            "Remainder,%",
            "Minus,~",
            "LessThan,<",
            "GreaterThan,>",
            "GreaterThan,greater?",
            "LessEqual,<=",
            "GreaterEqual,>=",
            "Equal,==",
            "NotEqual,!=",
            "And,&&",
            "And,and",
            "Or,||",
            "Not,!",
    })
    void testSpecialCharacters (String expected, String text) {
        assertEquals(expected,myClassifier.getSymbol(text));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "=",
            ":=",
            "=:",
            "+not",
            "not+",
            "++",
            "+*+",
    })
    void testIncorrectSpecialCharacters (String text) {
        assertEquals(Classifier.NO_MATCH,myClassifier.getSymbol(text));
    }

    @Test
    void testDifferentLanguage () {
        // compare languages directly without Syntax patterns
        myClassifier.reset();
        myClassifier.addPatterns("English");
        // verify ONLY English commands match
        assertEquals("Backward",myClassifier.getSymbol("back"));
        assertEquals("Backward",myClassifier.getSymbol("bk"));
        assertEquals(Classifier.NO_MATCH,myClassifier.getSymbol("derriere"));
        assertEquals(Classifier.NO_MATCH,myClassifier.getSymbol("der"));

        // switch languages
        myClassifier.reset();
        myClassifier.addPatterns("French");
        // verify ONLY French commands match
        assertEquals("Backward",myClassifier.getSymbol("derriere"));
        assertEquals("Backward",myClassifier.getSymbol("der"));
        assertEquals(Classifier.NO_MATCH,myClassifier.getSymbol("back"));
        assertEquals(Classifier.NO_MATCH,myClassifier.getSymbol("bk"));
    }

    @Test
    void testOrderAddedMatchesAllSpecificCommandsAsUserCommands () {
        assertEquals("Left",myClassifier.getSymbol("left"));
        assertEquals("Right",myClassifier.getSymbol("rt"));

        // switch order added so Syntax is first
        myClassifier.reset();
        myClassifier.addPatterns("Syntax");
        myClassifier.addPatterns("English");

        assertEquals("UserCommand",myClassifier.getSymbol("left"));
        assertEquals("UserCommand",myClassifier.getSymbol("rt"));
    }
}