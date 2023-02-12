package parser;

import dukeexeption.MissingArgumentException;

import command.Command;
import command.FindCommand;

/**
 * Parser that handles command that starts with find keyword.
 */
public class FindParser implements Parser {
    @Override
    public Command parse(String requestContent) throws MissingArgumentException {
        if ("".equals(requestContent.trim())) {
            throw new MissingArgumentException("The query string cannot be empty. " + "Format: find [query]");
        }
        String query = requestContent.trim();
        return new FindCommand(query);
    }
}
