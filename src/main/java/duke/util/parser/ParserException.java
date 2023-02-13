package duke.util.parser;

import duke.common.exception.DukeRuntimeException;

public class ParserException extends DukeRuntimeException {

    public ParserException() {}

    public ParserException(String message) {
        super(message);
    }
}
