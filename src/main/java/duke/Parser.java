package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Encapsulates the parsing of user input.
 */
public class Parser {

    /**
     * Parses user inputs to create a corresponding task.
     *
     * @param inputs from the user input
     * @return Task that user wants to add
     * @throws DukeException when user input does not follow expected format
     */
    private static Task parseTaskCommands(String[] inputs) throws DukeException {
        String taskType = inputs[0];
        if (inputs.length <= 1) {
            throw new DukeException("Oops!! The description of a " + taskType + " cannot be empty!");
        }

        String rest = inputs[1];
        Task task;

        if (taskType.equals("todo")) {
            task = new Todo(rest);
        } else if (taskType.equals("deadline")) {
            String[] addon = rest.split(" /", 2);
            String description = addon[0];
            if (addon.length <= 1) {
                throw new DukeException("Oops!! I do not have enough information to create a deadline task!\n"
                        + "You might be missing a date or the task description.");
            } else {
                String byString = addon[1];
                String[] byPart = byString.split(" ", 2);
                String by = byPart[1];
                task = new Deadline(description, by);
            }
        } else {
            // event
            String[] addon = rest.split(" /", 3);
            String description = addon[0];
            if (addon.length <= 1) {
                throw new DukeException("Oops!! I do not have enough information to create a event task!\n"
                        + "Please ensure you have indicated the duration using /from and /to!");
            } else {
                String fromString = addon[1];
                String[] fromPart = fromString.split(" ", 2);
                String from = fromPart[1];
                String toString = addon[2];
                String[] toPart = toString.split(" ", 2);
                String to = toPart[1];
                task = new Event(description, from, to);
            }
        }
        return task;

    }

    /**
     * Parses user inputs to create a Mark or Delete command.
     *
     * @param inputs from the user input
     * @return a Mark or Delete Command
     * @throws DukeException when user input does not follow expected format
     */
    private static Command parseMarkAndDeleteCommands(String[] inputs, String command) throws DukeException {
        if (inputs.length <= 1) {
            throw new DukeException("Please input the numbering of the task you want to " + command + " as well!");
        }

        String number = inputs[1]; // might throw Number Format Exception here
        int num = Integer.parseInt(number);
        if (command.equals("mark")) {
            return new MarkCommand(true, num - 1);
        } else if (command.equals("unmark")) {
            return new MarkCommand(false, num - 1);
        } else {
            return new DeleteCommand(num - 1);
        }
    }

    /**
     * Parses user inputs to create a Find Command.
     *
     * @param inputs from the user input
     * @return a Find Command with user-given keyword
     * @throws DukeException when user input does not follow expected format
     */
    private static Command parseFindCommands(String[] inputs) throws DukeException {
        if (inputs.length <= 1) {
            throw new DukeException("Please indicate the keyword you want to search with!");
        }

        String keyword = inputs[1];
        return new FindCommand(keyword);
    }


    /**
     * Parses the user input to obtain the intended Command.
     *
     * @param input read from System.in by the Ui
     * @return the corresponding Command
     * @throws DukeException when user input does not follow expected format.
     * @throws NumberFormatException whe user does input an integer for task number.
     */
    public static Command parse(String input) throws DukeException, NumberFormatException {
        String[] inputs = input.split(" ", 2);
        String command = inputs[0];

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();

        case "mark":
        case "unmark":
        case "delete":
            Command markOrDeleteCommand = parseMarkAndDeleteCommands(inputs, command);
            return markOrDeleteCommand;

        case "todo":
        case "deadline":
        case "event":
            Task task = parseTaskCommands(inputs);
            return new AddCommand(task);

        case "find":
            Command findCommand = parseFindCommands(inputs);
            return findCommand;

        default:
            throw new DukeException("Sorry I don't understand this command! :(");
        }
    }
}
