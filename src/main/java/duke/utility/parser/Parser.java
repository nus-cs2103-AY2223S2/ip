package duke.utility.parser;

import duke.duke_exception.DukeException;
import duke.tasklist.TaskList;
import duke.tasklist.task_types.Deadline;
import duke.tasklist.task_types.Event;
import duke.tasklist.task_types.Task;
import duke.tasklist.task_types.ToDo;
import duke.utility.ui.UiMessage;

public class Parser {
    private static DukeException wrongNumberParam =
            new DukeException("Wrong number of parameters inserted.");
    private static DukeException noParamCommand =
            new DukeException("Command cannot have any parameters");
    private static DukeException noNumericParam =
            new DukeException("Parameter is not a numerical value.");
    private static DukeException noSpecialParam =
            new DukeException("Missing special param e.g /by, /from, /to");
    private static DukeException emptyParam = new DukeException("Empty parameter inserted.");

    private static UiMessage printTasks(String[] command, TaskList list) throws DukeException {
        if (command.length > 1) {
            throw noParamCommand;
        }
        return new UiMessage(CommandMap.list, null);
    }

    private static UiMessage markTask(String[] command, TaskList list) throws DukeException {
        try {
            if (command.length > 2) {
                throw wrongNumberParam;
            }
            int index = Integer.parseInt(command[1]) - 1;
            list.markedTask(index);
            return new UiMessage(CommandMap.mark, list.getTask(index));
        } catch (NumberFormatException e) {
            throw noNumericParam;
        }

    }

    private static UiMessage unmarkTask(String[] command, TaskList list) throws DukeException {
        try {
            if (command.length > 2) {
                throw wrongNumberParam;
            }
            int index = Integer.parseInt(command[1]) - 1;
            list.unmarkedTask(index);
            return new UiMessage(CommandMap.unmark, list.getTask(index));
        } catch (NumberFormatException e) {
            throw noNumericParam;
        }

    }

    private static UiMessage deleteTask(String[] command, TaskList list) throws DukeException {
        try {
            if (command.length > 2) {
                throw wrongNumberParam;
            }
            int index = Integer.parseInt(command[1]) - 1;
            list.deleteTask(index);
            return new UiMessage(CommandMap.delete, list.getTask(index));
        } catch (NumberFormatException e) {
            throw noNumericParam;
        }
    }

    private static UiMessage closeProgram() {
        return new UiMessage(CommandMap.bye, null);
    }

    private static UiMessage createToDo(String[] command, TaskList list) throws DukeException {
        if (command.length > 2) {
            throw wrongNumberParam;
        }

        if (command.length == 1) {
            throw emptyParam;
        }

        Task toDoObj = new ToDo(command[1]);
        list.addTask(toDoObj);

        return new UiMessage(CommandMap.todo, toDoObj);
    }

    private static UiMessage createDeadline(String[] command, TaskList list) throws DukeException {
        if (command.length != 4) {
            throw wrongNumberParam;
        }

        if (!command[2].equals("/by")) {
            throw noSpecialParam;
        }

        Task deadlineObj = new Deadline(command[1], command[3]);
        list.addTask(deadlineObj);

        return new UiMessage(CommandMap.deadline, deadlineObj);
    }

    private static UiMessage createEvent(String[] command, TaskList list) throws DukeException {
        if (command.length != 6) {
            throw wrongNumberParam;
        }

        if (!command[2].equals("/from") || !command[4].equals("/to")) {
            throw noSpecialParam;
        }

        Task eventObj = new Event(command[1], command[3], command[5]);
        list.addTask(eventObj);

        return new UiMessage(CommandMap.event, eventObj);
    }

    private static UiMessage findTasks(String[] command, TaskList list) throws DukeException {
        if (command.length > 2) {
            throw wrongNumberParam;
        }

        if (command.length == 1) {
            throw emptyParam;
        }

        String keyword = command[1];


        return new UiMessage(CommandMap.find, new Task(keyword));
    }

    public static UiMessage readCommand(String input, TaskList list) throws DukeException {
        String[] command = input.trim().split(" ");

        try {
            switch (CommandMap.valueOf(command[0])) {
                case list:
                    return printTasks(command, list);
                case mark:
                    return markTask(command, list);
                case unmark:
                    return unmarkTask(command, list);
                case delete:
                    return deleteTask(command, list);
                case bye:
                    return closeProgram();
                case deadline:
                    return createDeadline(command, list);
                case event:
                    return createEvent(command, list);
                case find:
                    return findTasks(command, list);
                default:
                    return createToDo(command, list);
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("Invalid command.");
        }

    }

}
