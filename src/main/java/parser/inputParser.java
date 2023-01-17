package parser;

import exception.MissingArgumentException;
import response.ListResponse;
import response.Response;
import response.CreateResponse;

/**
 * Represents the parser for the user input
 */
public class InputParser {
    /**
     * Represents the user input
     */
    private String input;

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
    public Response parse() {
        switch (this.input.toLowerCase()) {
            case "":
                throw new MissingArgumentException("Please enter an input for me to parse!");
            case "list":
                return new ListResponse();
            default:
                return new CreateResponse(this.input);
        }
    }
}
