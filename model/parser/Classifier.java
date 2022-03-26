package slogo.model.parser;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @author Kushagra Ghosh
 */
public class Classifier {
    public static final String NO_MATCH = "NO MATCH";

    // where to find resources specifically for this class (slogo.languages)
    private static final String RESOURCES_PACKAGE = slogo.Main.class.getPackageName() + ".languages.";

    // "types" and the regular expression patterns that recognize those types
    // note, it is a list because order matters (some patterns may be more generic)
    private List<Map.Entry<String, Pattern>> mySymbols;


    /**
     * Create an empty parser
     */
    public Classifier() {
        reset();
    }

    /**
     * Add given resource file to this language's recognized types
     */
    public void addPatterns (String syntax) {
        ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + syntax);
        for (String key : Collections.list(resources.getKeys())) {
            mySymbols.add(new AbstractMap.SimpleEntry<>(key,
                    // THIS IS THE IMPORTANT LINE
                    Pattern.compile(resources.getString(key), Pattern.CASE_INSENSITIVE)));
        }
    }

    /**
     * Returns type associated with given text or NO_MATCH none exists
     */
    public String getSymbol (String text) {
        text = ((text == null) ? text : text.toLowerCase());
        for (Map.Entry<String, Pattern> e : mySymbols) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }
        // perhaps this should throw an exception instead --- do not know context
        return NO_MATCH;
    }

    /**
     * Removes all types this parser recognizes
     */
    public void reset () {
        mySymbols = new ArrayList<>();
    }


    // Returns true only if given text matches given regular expression pattern
    private boolean match (String text, Pattern regex) {
        // THIS IS THE OTHER IMPORTANT LINE
        return text != null && regex.matcher(text.trim()).matches();
    }
}
