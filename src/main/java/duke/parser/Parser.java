package duke.parser;

import duke.exceptions.DukeException;
import duke.exceptions.DukeUnknownCommandException;
import duke.task.TaskType;
import duke.tasklist.TaskList;

import java.util.Arrays;

/**
 * Represents a Parser.
 * Parses user input into different TaskList methods.
 * @author pzhengze.
 */
public class Parser {
    /** Reference to the TaskList it is parsing for */
    private final TaskList tasks;

    /**
     * Constructor for Parser object.
     * @param tasks The TaskList to be parsed for.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses the command and calls the correct TaskList method.
     * @param command The user command to be parsed.
     * @return The feedback message to the user.
     * @throws DukeUnknownCommandException if the parser fails to understand the command.
     * @throws DukeException if an exception occurred while executing the command.
     */
    public String parseAndExecute(String command) throws DukeUnknownCommandException, DukeException {
        // Extracts the command the other details of the command
        String[] inputArray = command.split(" ");
        String fn = inputArray[0];
        String info = "";
        if (inputArray.length > 1) {
            info = String.join(" ", Arrays.copyOfRange(inputArray, 1, inputArray.length));
        }

        // Interprets the extracted function and executes the correct TaskList method.
        switch(fn) {
            case "list":
                return tasks.list();
            case "mark":
                return tasks.mark(info.strip());
            case "unmark":
                return tasks.unMark(info.strip());
            case "delete":
                return tasks.delete(info.strip());
            case "todo":
                return tasks.add(TaskType.ToDos, info.strip());
            case "deadline":
                return tasks.add(TaskType.Deadlines, info.strip());
            case "event":
                return tasks.add(TaskType.Events, info.strip());
            case "bye":
                return "\t Bye. Hope to see you again soon!";
            default:
                throw new DukeUnknownCommandException();
        }
    }
}
