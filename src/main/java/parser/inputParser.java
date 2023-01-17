package parser;

import exception.MissingArgumentException;
import response.ListResponse;
import response.Response;
import response.CreateResponse;

public class InputParser {
    private String input;
    private String inputType;

    public InputParser(String input) {
        this.input = input;
    }

    public Response parse() {
        switch (this.input.toLowerCase()) {
            case "":
                throw new MissingArgumentException("Please enter an input for me to parse!");
            case "bye":
                break;
            case "list":
                return new ListResponse();
            default:
                return new CreateResponse(this.input);
        }
        return null;
    }
}
