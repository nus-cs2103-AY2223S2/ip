package Parser;

import Commands.*;
import entities.Deadline;
import entities.Event;
import entities.Todo;
import enums.CommandEnums;
import exceptions.EmptyDescException;
import exceptions.InvalidInputException;
import entities.TaskList;

public class Parser {
    public Command parseCommand(String input) throws InvalidInputException {
        String[] split = input.split(" ");
        CommandEnums type = CommandEnums.valueOf(split[0].toUpperCase().strip());
        switch (type) {
        case LIST:
            return new ListCommand();

        case MARK:
            if (isValidCommand(split)) {
                return new MarkCommand();
            } else {
                throw new EmptyDescException("Sorry! you can't have empty descriptions!");
            }

        case UNMARK:
            if (isValidCommand(split)) {
                return new UnmarkCommand();
            } else {
                throw new EmptyDescException("Sorry! you can't have empty descriptions!");
            }

        case TODO:
            if (isValidCommand(split)) {
                return new TodoCommand();
            } else {
                throw new EmptyDescException("Sorry! you can't have empty descriptions!");
            }

        case DEADLINE:
            if (isValidCommand(split)) {
                return new DeadlineCommand();
            } else {
                throw new EmptyDescException("Sorry! you can't have empty descriptions!");
            }

        case EVENT:
            if (isValidCommand(split)) {
                return new EventCommand();
            } else {
                throw new EmptyDescException("Sorry! you can't have empty descriptions!");
            }

        case DELETE:
            if (isValidCommand(split)) {
                return new DeleteCommand();
            } else {
                throw new EmptyDescException("Sorry! you can't have empty descriptions!");
            }

        case DATE:
            if (isValidCommand(split)) {
                return new SameDateCommand();
            } else {
                throw new EmptyDescException("Sorry! you can't have empty descriptions!");
            }
        case BYE:
            return new ExitCommand();
        default:
            throw new InvalidInputException("Sorry! I have no idea what that means ??? >:c");
        }
    }

    public boolean parseText(String input, TaskList list) {
        String[] split = input.split(" \\| ");
        switch (split[0]) {
        case ("T"):
            Todo todo = new Todo(split[2]);
            if (split[1].equals("1")) {
                todo.setDone();
            } else {
                todo.setUndone();
            }
            list.addTask(todo);
            return true;
        case ("D"):
            Deadline deadline = new Deadline(split[2], split[3]);
            if (split[1].equals("1")) {
                deadline.setDone();
            } else {
                deadline.setUndone();
            }
            list.addTask(deadline);
            return true;
        case ("E"):
            Event event = new Event(split[2], split[3], split[4]);
            if (split[1].equals("1")) {
                event.setDone();
            } else {
                event.setUndone();
            }
            list.addTask(event);
            return true;
        default:
            return false;
        }
    }

    public String parseDescription(String input) throws InvalidInputException {
        String[] split = input.split(" ");
        if (isValidCommand(split)) {
            return split[1];
        } else {
            throw new InvalidInputException("Sorry! you can't have empty descriptions!");
        }
    }

    public int parseIndex(String input) throws InvalidInputException {
        String[] split = input.split(" ");
        return Integer.parseInt(split[1]) - 1;
    }

    public String parseDeadline(String input) {
        String[] split = input.split("/by");
        return split[1];
    }

    public String[] parseEvent(String input) {
        String[] split = input.split("/from");
        split = split[1].split("/to");
        String[] res = {split[0], split[1]};
        return res;
    }

    public boolean isValidCommand(String[] input) {
        if (input.length < 2) {
            return false;
        } else {
            return true;
        }
    }


}
