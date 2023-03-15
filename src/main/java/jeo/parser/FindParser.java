package jeo.parser;

import jeo.command.Command;
import jeo.command.FindCommand;
import jeo.exception.JeoException;

public class FindParser implements Parser {
    @Override
    public Command parse(String[] splitInput) throws JeoException {
        if (splitInput.length == 1) {
            throw new JeoException("Please enter a keyword.", "find");
        }
        String key = splitInput[1];
        return new FindCommand(key);
    }
}
