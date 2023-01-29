package Duke.Parser;

import Duke.Commands.*;
import Duke.entities.Deadline;
import Duke.entities.Event;
import Duke.entities.Todo;
import Duke.enums.CommandEnums;
import Duke.exceptions.DukeException;
import Duke.entities.TaskList;

public class Parser {
    public Command parseCommand(String input) throws DukeException, IllegalArgumentException {
        String[] split = input.split(" ");
        CommandEnums type;
        try {
            type = CommandEnums.valueOf(split[0].toUpperCase().strip());
        } catch(IllegalArgumentException e) {
            System.out.println("Sorry! I have no idea what that means ??? >:c");
            return null;
        }
            switch (type) {
            case LIST:
                return new ListCommand();

            case MARK:
                if (isEmptyCommand(split)) {
                    return new MarkCommand();
                } else {
                    throw new DukeException("Sorry! you can't have empty descriptions!");
                }

            case UNMARK:
                if (isEmptyCommand(split)) {
                    return new UnmarkCommand();
                } else {
                    throw new DukeException("Sorry! you can't have empty descriptions!");
                }

            case TODO:
                if (isEmptyCommand(split)) {
                    return new TodoCommand();
                } else {
                    throw new DukeException("Sorry! you can't have empty descriptions!");
                }

            case DEADLINE:
                if (isEmptyCommand(split)) {
                    return new DeadlineCommand();
                } else {
                    throw new DukeException("Sorry! you can't have empty descriptions!");
                }

            case EVENT:
                if (isEmptyCommand(split)) {
                    return new EventCommand();
                } else {
                    throw new DukeException("Sorry! you can't have empty descriptions!");
                }

            case DELETE:
                if (isEmptyCommand(split)) {
                    return new DeleteCommand();
                } else {
                    throw new DukeException("Sorry! you can't have empty descriptions!");
                }
            case DATE:
                if (isEmptyCommand(split)) {
                    return new SameDateCommand();
                } else {
                    throw new DukeException("Sorry! you can't have empty descriptions!");
                }
            case BYE:
                return new ExitCommand();
            default:
                throw new DukeException("Sorry! I have no idea what that means ??? >:c");
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

    public String parseDescription(String input) throws DukeException {
        String[] split = input.split(" ");
        if (isEmptyCommand(split)) {
            return split[1];
        } else {
            throw new DukeException("Sorry! you can't have empty descriptions!");
        }
    }

    public int parseIndex(String input) throws DukeException {
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

    public boolean isEmptyCommand(String[] input) {
        return input.length < 2;
    }


}
