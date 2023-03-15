package jeo.parser;

import static jeo.parser.JeoParser.TAG_PREFIX;

import java.util.ArrayList;

import jeo.exception.JeoException;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 * @author Goh Jun How
 * @version 0.3
 */
public class ParserUtil {

    /**
     * Concatenates a sub-array of strings and returns the result.
     * @param splitInput user input split by white spaces
     * @param startIndex start position in array
     * @param endIndex end position in array (excluded)
     * @return Concatenated string
     */
    public static String parseSubArrayString(String[] splitInput, int startIndex, int endIndex) {
        StringBuilder sb = new StringBuilder();
        for (int i = startIndex; i < endIndex; i++) {
            sb.append(splitInput[i]).append(" ");
        }
        return sb.toString().trim();
    }

    /**
     * Parses the given input string as an integer and returns it as an integer.
     * @param indexString input string
     * @param commandType Type of Command
     * @return integer parsed from input string
     * @throws JeoException Custom error if user input does not conform to the expected format
     */
    public static int parseIndex(String indexString, String commandType) throws JeoException {
        int i;
        try {
            i = Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            throw new JeoException("Task number needs to be an integer value.", commandType);
        }
        return i;
    }

    /**
     * Parses the given input arguments and returns an arraylist of tags.
     * @param splitInput user input split by white spaces
     * @param indexOfFirstTagPrefix index position of first valid tag prefix in array
     * @param commandType Type of Command
     * @return arraylist of tags
     * @throws JeoException Custom error if user input does not conform to the expected format
     */
    public static ArrayList<String> parseTags(String[] splitInput, int indexOfFirstTagPrefix, String commandType)
            throws JeoException {
        int tagPrefixCount = 0;
        int splitInputLength = splitInput.length;
        ArrayList<String> tags = new ArrayList<>();
        for (int i = indexOfFirstTagPrefix; i < splitInputLength; i++) {
            if (splitInput[i].equals(TAG_PREFIX)) {
                tagPrefixCount++;
            } else if (tagPrefixCount - tags.size() == 1) {
                tags.add(splitInput[i]);
            } else {
                throw new JeoException("Tag formatting is wrong! Tags come last, and must be followed "
                        + "by the /tag prefix, without spaces.", commandType);
            }
        }
        if (tagPrefixCount > tags.size()) {
            throw new JeoException("Missing tag after prefix /tag", commandType);
        }
        return tags;
    }
}
