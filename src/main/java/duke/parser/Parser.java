package duke.parser;

import duke.command.*;
import duke.exception.EmptyDescException;
import duke.exception.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class Parser {


    enum Commands {
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        LIST,
        BYE

    }



    public static Command parse(String fullCommand) throws UnknownCommandException, EmptyDescException {
        try {
            String[] inputArr = fullCommand.split(" ");
            Commands command = Commands.valueOf(inputArr[0].toUpperCase());
            String desc = "variable not initialised";
            StringBuilder sb = new StringBuilder();

            switch (command) {
                case TODO:
                    if (inputArr.length == 1) {
                        throw new EmptyDescException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    for (int i = 1; i < inputArr.length; i++) {
                        sb.append(inputArr[i]);
                        if (i != inputArr.length - 1) {
                            sb.append(" ");
                        }
                    }
                    Todo todo = new Todo(sb.toString().trim());
                    return new TodoCommand(todo);
                case DEADLINE:
                    if (inputArr.length == 1) {
                        throw new EmptyDescException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    for (int i = 1; i < inputArr.length; i++) {
                        if (inputArr[i].equals("/by")) {
                            desc = sb.toString();
                            sb.setLength(0);
                            continue;
                        }
                        sb.append(inputArr[i]);
                        if (i != inputArr.length - 1) {
                            sb.append(" ");
                        }
                    }
                    String[] dateTime = sb.toString().split(" ");
                    Deadline deadline = new Deadline(desc, dateTime[0], dateTime[1]);
                    return new DeadlineCommand(deadline);
                case EVENT:
                    if (inputArr.length == 1) {
                        throw new EmptyDescException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    String from = "local variable not initialised";
                    for (int i = 1; i < inputArr.length; i++) {
                        if (inputArr[i].equals("/from")) {
                            desc = sb.toString();
                            sb.setLength(0);
                            continue;
                        } else if (inputArr[i].equals("/to")) {
                            from = sb.toString();
                            sb.setLength(0);
                            continue;
                        }
                        sb.append(inputArr[i]);
                        if (i != inputArr.length - 1) {
                            sb.append(" ");
                        }
                    }
                    Event event = new Event(desc, from, sb.toString().trim());
                    return new EventCommand(event);
                case MARK:
                    return new MarkCommand(Integer.parseInt(inputArr[1]));
                case UNMARK:
                    return new UnmarkCommand(Integer.parseInt(inputArr[1]));
                case DELETE:
                    int inputIndex = Integer.parseInt(inputArr[1]);
                    return new DeleteCommand(inputIndex);
                case LIST:
                    return new ListCommand();
                case BYE:
                    return new ByeCommand();
                default:
                    throw new UnknownCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }
}
