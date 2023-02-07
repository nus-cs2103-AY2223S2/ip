package task;

import duke.*;

public class Parser {
    public void checkEmpty(String input, String command) throws DukeException {
        if (input.length() < command.length() + 1) {
            switch (command) {
                case "todo":
                    throw new EmptyTodoException();
                case "deadline":
                    throw new EmptyDeadlineException();
                case "event":
                    throw new EmptyEventException();
                case "mark": case "unmark": case "delete":
                    throw new EmptyListException();
            }
        }
    }

    public String convertEnum(Command c) {
        String res = "";
        switch (c) {
            case LIST: case MARK: case TODO: case EVENT:
            case DELETE: case UNMARK: case DEADLINE: case BYE:
                res = c.name().toLowerCase();
        }
        return res;
    }
}
