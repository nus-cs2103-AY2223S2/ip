package genie;

import genie.command.*;
import genie.exception.DukeException;
import genie.exception.EmptyInputException;
import genie.exception.InvalidInputException;

public class Parser {
    public Parser() {}
    public Command parse(String in) throws DukeException {
        String i = in.toLowerCase();
        if (i.equals("bye")) {
            return new ExitCommand();
        } else if (i.equals("list")) {
            return new ListCommand();
        } else {
            String command = i.split(" ")[0];
            int index = -1;
            switch (command) {
            case "mark":
                index = Integer.parseInt(i.split(" ")[1]);
                return new MarkCommand(index);
            case "unmark":
                index = Integer.parseInt(i.split(" ")[1]);
                return new UnmarkCommand(index);
            case "delete":
                index = Integer.parseInt(i.split(" ")[1]);
                return new DeleteCommand(index);
            case "todo":
            case "deadline" :
            case "event":
                String taskType = i.split(" ")[0];
                if (i.split(" ").length == 1) {
                    throw new EmptyInputException(taskType);
                }
                return new AddCommand(taskType, i);
            default:
                throw new InvalidInputException();
            }
        }
    }
    public boolean isACommand(String i) {
        return i.startsWith("unmark") ||
                i.startsWith("mark") ||
                i.startsWith("todo") ||
                i.startsWith("event") ||
                i.startsWith("deadline") ||
                i.startsWith("delete");
    }
}
