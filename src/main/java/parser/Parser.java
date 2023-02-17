package parser;

import exception.InvalidArgumentException;
import exception.MissingArgumentException;
import response.CreateResponse;
import response.DeadlineResponse;
import response.DeleteResponse;
import response.EventResponse;
import response.FindResponse;
import response.ListResponse;
import response.MarkResponse;
import response.Response;
import response.UnMarkResponse;

/**
 * Represents the parser for the user input
 */
public class Parser {

    private enum Types { LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, SORT }

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
    public Parser(String input) {
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
        case "DELETE":
            return new DeleteResponse(this.inputContent);
        case "FIND":
            return new FindResponse(this.inputContent);
        case "SORT":
            return new SortResponse(this.inputContent);
        default:
            throw new MissingArgumentException("I'm sorry, but I don't know what that means :-(");
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
    public void categorisation() throws MissingArgumentException, InvalidArgumentException {
        String normalisedInput = normaliseAllWhitespaces(input);
        String[] arrStr = normalisedInput.split(" ", 2);

        if (arrStr.length == 2) { // user input is more than one word
            try {
                String currType = arrStr[0].toUpperCase();
                Types.valueOf(currType); // checking if currType exists in Types
                this.inputType = currType;
                this.inputContent = arrStr[1];
            } catch (IllegalArgumentException e) {
                throw new InvalidArgumentException("Remember to specify the type of request in your input!");
            }
        } else if (arrStr.length == 1) { // user input is only one word
            this.inputType = arrStr[0].toUpperCase();
            this.inputContent = "";
        } else if (arrStr.length == 0) { // for the edge cases where user input " " or ""
            throw new MissingArgumentException("Please enter something for me to parse!");
        }
    }

    public String getInputType() {
        return this.inputType;
    }
}
