package duke.utility.parser;

import duke.duke_exception.DukeException;
import duke.tasklist.TaskList;
import duke.tasklist.task_types.Deadline;
import duke.tasklist.task_types.Event;
import duke.tasklist.task_types.Task;
import duke.tasklist.task_types.ToDo;
import duke.utility.enums.CommandMap;
import duke.utility.enums.UpdateType;

/**
 * Represents a <code>Parser</code> object that perfroms the necessary
 * operations based on the user
 * input.
 * 
 * @author Brian Quek
 */
public class Parser {
    private static DukeException wrongCommandFormat = new DukeException("Wrong command format inserted.\nType 'help' for more information.");
    private static DukeException noNumericParam = new DukeException("Parameter is not a numerical value.");
    private static DukeException noSpecialParam = new DukeException("Missing special param e.g /by, /from, /to");
    private static DukeException emptyParam = new DukeException("Empty parameter inserted.");
    private static final String HELP_COMMAND = "list \n " +
                                                "-displays the list of tasks.\n\n" +
                                                "todo [NAME]\n" +
                                                "-creates a todo task.\n\n" +
                                                "deadline [NAME] /by [YYYY-MM-DD]\n" +
                                                "-creates a deadline task.\n" +
                                                "event [name] /from [FROM] /to [TO]\n" +
                                                "-creates a event task.\n\n" +
                                                "mark [INDEX]\n" +
                                                "-marks the task based on its index.\n\n" +
                                                "unmark [INDEX]\n" +
                                                "-unmarks the task based on its index.\n\n" +
                                                "update [INDEX] [TYPE] [NEW_VALUE]\n" +
                                                "-updates the value of the task based on the index\n and type (E.g /name /by /from /to).\n\n" +
                                                "find [KEYWORD]\n" +
                                                "-finds all tasks' name that contains the keyword.\n\n" + 
                                                "help\n" + 
                                                "-returns command syntax that Pepe can offer.\n\n" + 
                                                "bye\n" +
                                                "-terminates program";

    /**
     * Prints the list of tasks stored in the TaskList.
     * 
     * @param command the command segments based on the user's input.
     * @param list    the TaskList to be displayed.
     * @return the reply String message.
     * @throws DukeException if the command format is invalid.
     */
    private static String printTasks(String[] command, TaskList list) throws DukeException {
        if (command.length > 1) {
            throw wrongCommandFormat;
        }

        String message = list.getTotal() + list.toString();
        return message;
    }

    /**
     * Marks a task in the task list.
     * 
     * @param command the command segments based on the user's input.
     * @param list    the list of tasks.
     * @return the reply String message.
     * @throws DukeException if the command format is invalid or invalid index
     *                       value.
     */
    private static String markTask(String[] command, TaskList list) throws DukeException {
        try {
            if (command.length > 2) {
                throw wrongCommandFormat;
            }
            int index = Integer.parseInt(command[1]) - 1;
            return list.markedTask(index);
        } catch (NumberFormatException e) {
            throw noNumericParam;
        }

    }

    /**
     * Unmark a task from the list.
     * 
     * @param command the command segments based on the user's input.
     * @param list    the list of tasks.
     * @return the reply String message.
     * @throws DukeException if the command format is invalid or invalid index
     *                       value.
     */
    private static String unmarkTask(String[] command, TaskList list) throws DukeException {
        try {
            if (command.length > 2) {
                throw wrongCommandFormat;
            }
            int index = Integer.parseInt(command[1]) - 1;
            return list.unmarkedTask(index);
        } catch (NumberFormatException e) {
            throw noNumericParam;
        }

    }

    /**
     * Deletes a task from the list based on the given index.
     * 
     * @param command the command segments based on the user's input.
     * @param list    the list of tasks.
     * @return the reply String message.
     * @throws DukeException if the command format is invalid or invalid index
     *                       value.
     */
    private static String deleteTask(String[] command, TaskList list) throws DukeException {
        try {
            if (command.length > 2) {
                throw wrongCommandFormat;
            }
            int index = Integer.parseInt(command[1]) - 1;
            return list.deleteTask(index);
        } catch (NumberFormatException e) {
            throw noNumericParam;
        }
    }

    /**
     * Terminates the bot program.
     * 
     * @return the reply String message.
     */
    private static String closeProgram(String[] command) throws DukeException {
        if (command.length > 1) {
            throw wrongCommandFormat;
        }
        return "Bye! See you soon!\n";
    }

    /**
     * Creates a toDo object and adds it into the list.
     * 
     * @param command the command segments based on the user's input.
     * @param list    the list of tasks.
     * @return the reply String message.
     * @throws DukeException if the command format is invalid or does not have any
     *                       parameter.
     */
    private static String createToDo(String[] command, TaskList list) throws DukeException {
        if (command.length > 2) {
            throw wrongCommandFormat;
        }

        if (command.length == 1) {
            throw emptyParam;
        }

        Task toDoObj = new ToDo(command[1]);
        return list.addTask(toDoObj);
    }

    /**
     * Creates a Deadline object and adds it into the list.
     * 
     * @param command the command segments based on the user's input
     * @param list    the list of tasks.
     * @return the reply String message.
     * @throws DukeException if the command format is invalid or missing any special
     *                       parameter (/by
     *                       e.g)
     */
    private static String createDeadline(String[] command, TaskList list) throws DukeException {

        if (command.length != 4) {
            throw wrongCommandFormat;
        }

        if (!command[2].equals("/by")) {
            throw noSpecialParam;
        }

        Task deadlineObj = new Deadline(command[1], command[3]);
        return list.addTask(deadlineObj);
    }

    /**
     * Creates an Event object and adds it into the list.
     * 
     * @param command the command segments based on the user's input
     * @param list    the list of tasks.
     * @return the reply String message.
     * @throws DukeException if the command format is invalid or missing any special
     *                       parameter (/by
     *                       e.g)
     */
    private static String createEvent(String[] command, TaskList list) throws DukeException {
        if (command.length != 6) {
            throw wrongCommandFormat;
        }

        if (!command[2].equals("/from") || !command[4].equals("/to")) {
            throw noSpecialParam;
        }

        Task eventObj = new Event(command[1], command[3], command[5]);
        return list.addTask(eventObj);
    }

    /**
     * Finds the task from the list.
     * 
     * @param command the command segments based on the user's input
     * @param list    the list of tasks.
     * @return the reply String message.
     * @throws DukeException if the command format is invalid or missing any special
     *                       parameter (/by
     *                       e.g)
     */
    private static String findTasks(String[] command, TaskList list) throws DukeException {
        if (command.length > 2) {
            throw wrongCommandFormat;
        }

        if (command.length == 1) {
            throw emptyParam;
        }

        String keyword = command[1];

        return list.filter(keyword);
    }

    /**
     * Updates a task from the list.
     * 
     * @param command the command segments based on the user's input
     * @param list    the list of tasks.
     * @return the reply String message.
     * @throws DukeException if the command format is invalid or missing any special
     *                       parameter (/by
     *                       e.g)
     */
    private static String updateTask(String[] command, TaskList list) throws DukeException {
        try {
            if (command.length != 4) {
                throw wrongCommandFormat;
            }
            int index = Integer.parseInt(command[1]) - 1;
            UpdateType type = UpdateType.parseCommand(command[2]);
            String newValue = command[3];

            return list.update(index, type, newValue);
        } catch (NumberFormatException e) {
            throw noNumericParam;
        }
    }

    private static String helpMenu(String[] command) throws DukeException {
        if (command.length != 1) {
            throw wrongCommandFormat;
        }

        return HELP_COMMAND;
    }

    /**
     * Reads the input of the user and parse the input accordingly.
     * 
     * @param input user input
     * @param list  list of tasks
     * @return the reply String message.
     * @throws DukeException if invalid command keyword is typed.
     */
    public static String readCommand(String input, TaskList list) throws DukeException {
        String[] command = input.trim().split(" ");
        assert command.length < 7 : "Command has too many parameters!";
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
                    return closeProgram(command);
                case deadline:
                    return createDeadline(command, list);
                case event:
                    return createEvent(command, list);
                case find:
                    return findTasks(command, list);
                case update:
                    return updateTask(command, list);
                case help:
                    return helpMenu(command); 
                default:
                    return createToDo(command, list);
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("Invalid command.\nType 'help' for more information.");
        }

    }

}
