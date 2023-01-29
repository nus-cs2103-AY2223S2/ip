package parser;

import command.Command;
import command.FindCommand;
import dukeexeption.MissingArgumentException;

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
