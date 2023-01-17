package parser;

import exception.InvalidArgumentException;
import exception.MissingArgumentException;
import response.ListResponse;
import response.Response;
import response.CreateResponse;
import response.MarkResponse;
import response.UnMarkResponse;

/**
 * Represents the parser for the user input
 */
public class InputParser {
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
    public Response parse() throws MissingArgumentException {
        categorisation();
        switch (this.inputType) {
            case "list":
                return new ListResponse();
            case "mark":
                return new MarkResponse(this.inputContent);
            case "unmark":
                return new UnMarkResponse(this.inputContent);
            default:
                return new CreateResponse(this.inputContent);
        }
    }

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

    public void categorisation() {
        String normalisedInput = normaliseAllWhitespaces(input);
        String[] arrStr = normalisedInput.split(" ", 2);

        if (arrStr.length == 2) { // user input is more than one word
            if (arrStr[0].equals("mark") || arrStr[0].equals("unmark")) { // user wants to mark/unmark something
                try {
                    Integer.parseInt(arrStr[1]);
                } catch (NumberFormatException nfe) {
                    throw new InvalidArgumentException("Enter a number after mark/unmark!");
                }

                this.inputType = arrStr[0].toLowerCase();
                this.inputContent = arrStr[1];
            } else { // treat it as a regular create new task response
                this.inputType = normalisedInput;
                this.inputContent = normalisedInput;
            }
        } else if (arrStr.length == 1) { // user input is only one word (either list or create)
            this.inputType = arrStr[0].toLowerCase();
            this.inputContent = arrStr[0];
        } else if (arrStr.length == 0) { // for the edge cases where user input " " or ""
            throw new MissingArgumentException("Please enter something for me to parse!");
        }
    }
}
