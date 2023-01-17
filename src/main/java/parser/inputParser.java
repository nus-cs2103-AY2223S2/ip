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

    public void categorisation() {
        String[] arrStr = input.split(" ", 2);

        if (arrStr.length > 1) {
            if (arrStr[0].equals("")) {
                throw new MissingArgumentException("Remember to tell me what you want me to do!");
            }

            try {
                Integer.parseInt(arrStr[1]);
            } catch (NumberFormatException nfe) {
                throw new InvalidArgumentException("Enter a number after mark/unmark!");
            }

            this.inputType = arrStr[0].toLowerCase();
            this.inputContent = arrStr[1];
        } else if (arrStr.length == 1 && !arrStr[0].equals("")) {
            this.inputType = arrStr[0].toLowerCase();
            this.inputContent = arrStr[0];
        } else if (arrStr.length == 0) {
            throw new MissingArgumentException("Please enter something for me to parse!");
        }

    }
}
