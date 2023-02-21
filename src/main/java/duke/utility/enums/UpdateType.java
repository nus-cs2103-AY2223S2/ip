package duke.utility.enums;

import duke.duke_exception.DukeException;

public enum UpdateType {
    name, by, from, to;

    private static DukeException wrongCommand = new DukeException("Empty parameter inserted.");

    public static UpdateType parseCommand(String input) throws DukeException {

        switch(input) {
        case "/name":
            return name;
        case "/by":
            return by;
        case "/from":
            return from;
        case "/to":
            return to;
        default:
            throw wrongCommand;
        }
        
    }
}

