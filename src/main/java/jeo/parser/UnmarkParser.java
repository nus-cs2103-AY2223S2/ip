package jeo.parser;

import jeo.command.Command;
import jeo.command.UnmarkCommand;
import jeo.exception.JeoException;

public class UnmarkParser implements Parser {
    @Override
    public Command parse(String[] splitInput) throws JeoException {
        if (splitInput.length == 1) {
            throw new JeoException("Please enter a task number.", "unmark");
        }
        int index = ParserUtil.parseIndex(splitInput[1], "unmark")-1;
        return new UnmarkCommand(index);
    }
}
