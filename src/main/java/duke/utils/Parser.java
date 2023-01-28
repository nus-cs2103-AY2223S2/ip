package duke.utils;

import duke.commands.*;
import duke.exceptions.*;
import duke.tasks.*;
import duke.ui.Ui;

import java.io.File;

public class Parser {

    public Command parse(String userCommands, TaskList commandList, Storage storage, Ui ui, File file)
            throws InvalidCmdValueException, InvalidTaskTypeException, EmptyCommandException,
            InvalidTimeException, InvalidDateException {
        String[] strArray = userCommands.split(" ", 2);
        String action = strArray[0];

        if (action.equalsIgnoreCase("bye")) {
            return new ByeCommand(ui);
        } else if (action.equalsIgnoreCase("list")) {
            return new ListCommand(ui, commandList);
        } else if (action.equalsIgnoreCase("mark")) {
            try {
                return new MarkCommand(ui, commandList,
                        Integer.parseInt(strArray[1]) - 1, storage, file);
            } catch (InvalidCmdValueException e) {
                throw e;
            }
        } else if (action.equalsIgnoreCase("unmark")) {
            try {
                return new UnmarkCommand(ui, commandList,
                        Integer.parseInt(strArray[1]) - 1, storage, file);
            } catch (InvalidCmdValueException e) {
                throw e;
            }
        } else if (action.equalsIgnoreCase("delete")) {
            try {
                return new DeleteCommand(ui, commandList,
                        Integer.parseInt(strArray[1]) - 1, storage, file);
            } catch (InvalidCmdValueException e) {
                throw e;
            }
        } else {
            try {
                TaskTypes type = getTaskType(action);
                Task task = getTask(type, strArray);
                return new AddCommand(ui, commandList, task, storage, file);
            } catch (InvalidTaskTypeException | EmptyCommandException | InvalidTimeException |
                     InvalidDateException e) {
                throw e;
            }
        }
    }

    public TaskTypes getTaskType(String action) throws InvalidTaskTypeException {
        if (action.equalsIgnoreCase("todo")) {
            return TaskTypes.TODO;
        } else if (action.equalsIgnoreCase("deadline")) {
            return TaskTypes.DEADLINE;
        } else if (action.equalsIgnoreCase("event")) {
            return TaskTypes.EVENT;
        } else {
            throw new InvalidTaskTypeException();
        }
    }

    public Task getTask(TaskTypes type, String[] strArray) throws InvalidTaskTypeException,
            EmptyCommandException, InvalidTimeException, InvalidDateException {
        String command;

        if (strArray.length < 2 || strArray[1].trim().equals("")) {
            throw new EmptyCommandException(strArray[0]);
        }

        if (type.equals(TaskTypes.TODO)) {
            command = strArray[1].trim();
            return new ToDo(command);
        } else if (type.equals(TaskTypes.DEADLINE)) {
            String[] temp = strArray[1].split("/by", 2);
            if (temp.length < 2 || temp[1].trim().equals("")) {
                throw new InvalidTimeException();
            }
            command = temp[0].trim();
            String deadline = temp[1].trim();
            return new Deadline(command, deadline);
        } else if (type.equals(TaskTypes.EVENT)) {
            String[] temp = strArray[1].split("/from", 2);
            if (temp.length < 2 || temp[1].trim().equals("")) {
                throw new InvalidTimeException();
            }
            String[] temp2 = temp[1].split("/to", 2);
            if (temp2.length < 2 || temp2[1].trim().equals("")) {
                throw new InvalidTimeException();
            }
            command = temp[0].trim();
            String start = temp2[0].trim();
            String end = temp2[1].trim();
            return new Event(command, start, end);
        } else {
            throw new InvalidTaskTypeException();
        }
    }
}
