package parser;

import exception.InvalidArgumentException;
import exception.MissingArgumentException;
import response.*;

/**
 * Represents the parser for the user input
 */
public class InputParser {

    private enum Types {LIST, MARK, UNMARK, TODO, DEADLINE, EVENT}

    /**
     * Represents the user input
     */
    private String input;
    private String inputType;
    private String inputContent;


    /**
     * Creates a parser for the specified user input
     * @param input User input
     */
    public InputParser(String input) {
        this.input = input;
    }

    /**
     * Parses the user input
     * @return Response returned, depending on the user input parsed
     */
    public Response parse() throws MissingArgumentException, InvalidArgumentException {
        categorisation();
        switch (this.inputType) {
            case "LIST":
                return new ListResponse();
            case "MARK":
                return new MarkResponse(this.inputContent);
            case "UNMARK":
                return new UnMarkResponse(this.inputContent);
            case "TODO":
                return new CreateResponse(this.inputContent);
            case "DEADLINE":
                return new DeadlineResponse(this.inputContent);
            case "EVENT":
                return new EventResponse(this.inputContent);
            default:
                throw new MissingArgumentException("idk whats going on bro");
        }
    }

    /**
     * Normalises all the whitespaces in a string
     * @param s Input string to normalise
     * @return The normalised string
     */
    public String normaliseAllWhitespaces(String s) {
        int l = s.length();
        StringBuilder sb = new StringBuilder(l);

        boolean lastWasWhite = false;
        boolean modified = false;

        for (int i = 0; i < l; i++) {
            int c = s.codePointAt(i);
            if (Character.isWhitespace(c)) {
                if (lastWasWhite || (c == ' ' && i == 0)) {
                    modified = true;
                    lastWasWhite = true;
                    continue;
                }

                if (c != ' ') {
                    modified = true;
                }

                sb.append(' ');
                lastWasWhite = true;
            } else {
                sb.appendCodePoint(c);
                lastWasWhite = false;
            }
        }
        return modified ? sb.toString() : s;
    }

    /**
     * Some preprocessing to categorise what type of request the user is inputting
     */
    public void categorisation() {
        String normalisedInput = normaliseAllWhitespaces(input);
        String[] arrStr = normalisedInput.split(" ", 2);

        if (arrStr.length == 2) { // user input is more than one word
            try {
                String currType = arrStr[0].toUpperCase();
                Types.valueOf(currType);

                if (currType.equals("MARK") || currType.equals("UNMARK")) {
                    Integer.parseInt(arrStr[1]);
                }

                this.inputType = currType;
                this.inputContent = arrStr[1];
            } catch (NumberFormatException e) {
                throw new InvalidArgumentException("Enter a number after mark/unmark!");
            } catch (IllegalArgumentException e) {
                throw new InvalidArgumentException("Remember to specify the type of request in your input!");
            }

//            if (arrStr[0].equals("mark")
//                    || arrStr[0].equals("unmark")) { // user wants to mark/unmark something
//                try {
//                    Integer.parseInt(arrStr[1]);
//                } catch (NumberFormatException nfe) {
//                    throw new InvalidArgumentException("Enter a number after mark/unmark!");
//                }
//
//                this.inputType = arrStr[0].toLowerCase();
//                this.inputContent = arrStr[1];
//            } else if (arrStr[0].equals("todo")
//                    || arrStr[0].equals("deadline")
//                    || arrStr[0].equals("event")) {
//                this.inputType = arrStr[0];
//                this.inputContent = arrStr[1];
//            } else {
//                throw new InvalidArgumentException("Remember to specify the type of request in your input!")
//            }
        } else if (arrStr.length == 1) { // user input is only one word (either list or create)
            this.inputType = arrStr[0].toUpperCase();
            this.inputContent = arrStr[0];
        } else if (arrStr.length == 0) { // for the edge cases where user input " " or ""
            throw new MissingArgumentException("Please enter something for me to parse!");
        }
    }
}
