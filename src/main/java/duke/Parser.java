package duke;

import commands.*;
import exceptions.DukeException;
import tasks.*;

public class Parser {

    public static CommandType getCommandType(String input) {
        for (CommandType c : CommandType.values()) {
            if (c.equals(input)) {
                return c;
            }
        }
        return CommandType.UNKNOWN;
    }

    public static boolean isDescriptionEmpty(String[] arr) {
        return arr.length == 1 || arr[1].trim().isEmpty();
    }

    public static Command parse(String input) throws DukeException {
        String[] inputArr = input.split(" ", 2);
        CommandType commandType = getCommandType(inputArr[0]);
        int index = 0;
        String taskInput = "";

        switch (commandType) {
            case LIST:
                return new ListCommand();
            case MARK:
            case UNMARK:
                index = Integer.parseInt(inputArr[1]) - 1;
                return new MarkCommand(commandType, index);
            case DELETE:
                index = Integer.parseInt(inputArr[1]) - 1;
                return new DeleteCommand(index);
            case TODO:
                if (isDescriptionEmpty(inputArr)) {
                    throw new DukeException("Hey now.. The description of a todo cannot be empty. >:(");
                }
                taskInput = inputArr[1];
                Todo todo = new Todo(taskInput);
                return new AddCommand(CommandType.TODO, todo);
            case DEADLINE:
                if (isDescriptionEmpty(inputArr)) {
                    throw new DukeException("Hey now.. The description of a deadline cannot be empty. >:(");
                }
                taskInput = inputArr[1];
                String[] dArr = taskInput.split("/by", 2);
                String deadlineDesc = dArr[0].trim();
                String by = dArr[1].trim();
                Deadline deadline = new Deadline(deadlineDesc, by);
                return new AddCommand(CommandType.DEADLINE, deadline);
            case EVENT:
                if (isDescriptionEmpty(inputArr)) {
                    throw new DukeException("Hey now.. The description of an event cannot be empty. >:(");
                }
                taskInput = inputArr[1];
                String[] eArr = taskInput.split("/");
                String eventDesc = eArr[0].trim();
                String from = eArr[1].trim().substring(5);
                String to = eArr[2].trim().substring(3);
                Event event = new Event(eventDesc, from, to);
                return new AddCommand(CommandType.EVENT, event);
            case BYE:
                return new ByeCommand();
            default:
                throw new DukeException("Huh? What do you mean? :o");
        }
    }
}
