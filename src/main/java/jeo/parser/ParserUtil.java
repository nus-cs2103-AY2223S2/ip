package jeo.parser;

import jeo.exception.JeoException;

import java.util.ArrayList;

import static jeo.parser.JeoParser.TAG_PREFIX;

public class ParserUtil {
    public static String parseSubArrayString(String[] splitInput, int startIndex, int endIndex) {
        StringBuilder sb = new StringBuilder();
        for (int i = startIndex; i < endIndex; i++) {
            sb.append(splitInput[i]).append(" ");
        }
        return sb.toString().trim();
    }

    public static int parseIndex(String indexString, String commandType) throws JeoException {
        int i;
        try {
            i = Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            throw new JeoException("Task number needs to be an integer value.", commandType);
        }
        return i;
    }

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
                throw new JeoException("Tag formatting is wrong! Tags come last, and must be followed " +
                        "by the /tag prefix, without spaces.", commandType);
            }
        }
        if (tagPrefixCount > tags.size()) {
            throw new JeoException("Missing tag after prefix /tag", commandType);
        }
        return tags;
    }
}
