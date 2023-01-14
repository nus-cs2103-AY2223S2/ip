package parser;

import command.Command;

public interface Parser {
    /**
     * Parses a request to generate command.
     * @param requestContent    content to be parsed
     * @return  A corresponding command.
     */
    Command parse(String requestContent);
}
