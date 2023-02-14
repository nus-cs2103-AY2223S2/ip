package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.exception.DukeEmptyArgumentException;
import duke.exception.DukeEventOverlapException;
import duke.exception.DukeInvalidArgumentException;

public class ParserStub {
    public static Command parse(String input) throws DukeEmptyArgumentException,
            DukeInvalidArgumentException, DukeEventOverlapException {
        if (input.equals("todo do ip")) {
            return new AddCommand(new String[]{"todo", "do ip"});
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
