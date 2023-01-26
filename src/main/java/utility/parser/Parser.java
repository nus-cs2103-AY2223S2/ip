package utility.parser;

import duke_exception.DukeException;
import tasklist.TaskList;
import tasklist.task_types.Deadline;
import tasklist.task_types.Event;
import tasklist.task_types.Task;
import tasklist.task_types.ToDo;

public class Parser {
    private static DukeException wrongNumberParam = new DukeException("Wrong number of parameters inserted.");
    private static DukeException noParamCommand = new DukeException("Command cannot have any parameters");
    private static DukeException noNumericParam = new DukeException("Parameter is not a numerical value.");
    private static DukeException noSpecialParam = new DukeException("Missing special param e.g /by, /from, /to");
    private static DukeException emptyParam = new DukeException("Empty parameter inserted.");

    private static CommandMap printTasks(String[] command, TaskList list) throws DukeException {
        if (command.length > 1) {
            throw noParamCommand;
        }
        System.out.println(list);
        return CommandMap.list;
    }

    private static CommandMap markTask(String[] command, TaskList list) throws DukeException {
        try {
            if (command.length > 2) {
                throw wrongNumberParam;
            }
            list.markedTask(Integer.parseInt(command[1]) - 1);
            return CommandMap.mark;
        } catch (NumberFormatException e) {
            throw noNumericParam;
        }

    }

    private static CommandMap unmarkTask(String[] command, TaskList list) throws DukeException {
        try {
            if (command.length > 2) {
                throw wrongNumberParam;
            }
            list.unmarkedTask(Integer.parseInt(command[1]) - 1);
            return CommandMap.unmark;
        } catch (NumberFormatException e) {
            throw noNumericParam;
        }

    }

    private static CommandMap deleteTask(String[] command, TaskList list) throws DukeException {
        try {
            if (command.length > 2) {
                throw wrongNumberParam;
            }
            list.deleteTask(Integer.parseInt(command[1]) - 1);
            return CommandMap.unmark;
        } catch (NumberFormatException e) {
            throw noNumericParam;
        }
    }

    private static CommandMap closeProgram() {
        return CommandMap.bye;
    }

    private static CommandMap createToDo(String[] command, TaskList list) throws DukeException {
        if (command.length > 2) {
            throw wrongNumberParam;
        }

        if (command.length == 1) {
            throw emptyParam;
        }

        Task toDoObj = new ToDo(command[1]);
        list.addTask(toDoObj);

        return CommandMap.todo;
    }

    private static CommandMap createDeadline(String[] command, TaskList list) throws DukeException {
        if (command.length != 4) {
            throw wrongNumberParam;
        }

        if (!command[2].equals("/by")) {
            throw noSpecialParam;
        }

        Task deadlineObj = new Deadline(command[1], command[3]);
        list.addTask(deadlineObj);

        return CommandMap.deadline;
    }

    private static CommandMap createEvent(String[] command, TaskList list) throws DukeException {
        if (command.length != 6) {
            throw wrongNumberParam;
        }

        if (!command[2].equals("/from") || !command[4].equals("/to")) {
            throw noSpecialParam;
        }

        Task eventObj = new Event(command[1], command[3], command[5]);
        list.addTask(eventObj);

        return CommandMap.event;
    }

    public static CommandMap readCommand(String input, TaskList list) throws DukeException {
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
                default:
                    return createToDo(command, list);
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("Invalid command.");
        }

    }

}
