package duke.parser;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Parser {
    public int getTaskNum(String[] command) throws DukeException {
        if (command.length < 2) {
            throw new DukeException("Task number required");
        }
        return Integer.parseInt(command[1]) - 1;
    }

    public Task getTaskToAdd(String[] command) throws DukeException {
        if (command.length < 2) {
            throw new DukeException("Invalid input");
        }

        switch (command[0]) {
            case "todo":
                return new Todo(command[1]);
            case "deadline": {
                String[] arguments = command[1].split(" /by ");
                if (arguments.length < 2) {
                    throw new DukeException("Deadline needs a \"by date\"");
                }
                return new Deadline(arguments[0], arguments[1]);
            }
            case "event": {
                String[] arguments = command[1].split(" /from ");
                if (arguments.length < 2) {
                    throw new DukeException("invalid format");
                }
                String[] timings = arguments[1].split(" /to ");
                if (timings.length < 2) {
                    throw new DukeException("invalid format");
                }
                return new Event(arguments[0], timings[0], timings[1]);
            }
            default:
                throw new DukeException("I do not understand");
        }
    }

    public String getKeyword(String[] command) throws DukeException {
        if (command.length < 2) {
            throw new DukeException("Keyword required");
        }

        return command[1];
    }
}