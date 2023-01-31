package duke.exception.parserexceptions;

import duke.exception.DukeException;

public class ParserException extends DukeException {
    public ParserException() {
        super("\n" + "    ____________________________________________________________\n" +
                "Oh my, I'm gonna need a command body!" + "\n" +
                "    ____________________________________________________________\n");
    }
}
