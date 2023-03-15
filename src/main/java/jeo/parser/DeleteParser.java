package jeo.parser;

import jeo.command.Command;
import jeo.command.DeleteCommand;
import jeo.exception.JeoException;

public class DeleteParser implements Parser {
    @Override
    public Command parse(String[] splitInput) throws JeoException {
        if (splitInput.length == 1) {
            throw new JeoException("Please enter a task number.", "delete");
        }
        int index = ParserUtil.parseIndex(splitInput[1], "delete")-1;
        return new DeleteCommand(index);
    }
}
