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
                throw new MissingArgumentException(
                        "I'm sorry, but I don't know what that means :-(" +
                                "\n\t  To create a new todo, use 'todo ...'," +
                                "\n\t  To create a new event, use 'event ... /from ... /to ...'," +
                                "\n\t  To create a new deadline, use 'deadline ... /by ...'," +
                                "\n\t  To list all tasks, use 'list'," +
                                "\n\t  To mark a task, use 'mark ' with a number," +
                                "\n\t  To unmark a task, use 'unmark ' with a number," +
                                "\n\t  Finally to exit the program, use 'bye'!");
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
                Types.valueOf(currType); // checking if currType exists in Types
                this.inputType = currType;
                this.inputContent = arrStr[1];
            } catch  (IllegalArgumentException e) {
                throw new InvalidArgumentException("Remember to specify the type of request in your input!");
            }
        } else if (arrStr.length == 1) { // user input is only one word
            this.inputType = arrStr[0].toUpperCase();
            this.inputContent = "";
        } else if (arrStr.length == 0) { // for the edge cases where user input " " or ""
            throw new MissingArgumentException("Please enter something for me to parse!");
        }
    }
}
