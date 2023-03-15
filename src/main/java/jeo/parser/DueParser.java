package jeo.parser;

import jeo.command.Command;
import jeo.command.DueCommand;
import jeo.exception.JeoException;

public class DueParser implements Parser {
    @Override
    public Command parse(String[] splitInput) throws JeoException {
        if (splitInput.length == 1) {
            throw new JeoException("Please enter a date.", "due");
        }
        String by = splitInput[1];
        return new DueCommand(by);
    }
}
