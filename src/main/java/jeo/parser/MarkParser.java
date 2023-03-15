package jeo.parser;

import jeo.command.Command;
import jeo.command.MarkCommand;
import jeo.exception.JeoException;

public class MarkParser implements Parser {

    @Override
    public Command parse(String[] splitInput) throws JeoException {
        if (splitInput.length == 1) {
            throw new JeoException("Please enter a task number.", "mark");
        }
        int index = ParserUtil.parseIndex(splitInput[1], "mark")-1;
        return new MarkCommand(index);
    }
}
