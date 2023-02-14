package duke.util;

import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.command.Command;
import duke.task.TaskList;

/**
 * The Parser class deals with making sense of the user command.
 * @author Junyi
 */
public class Parser {

    /* TaskList instance of tasks */
    private final TaskList taskList;

    /* Storage instance of Duke */
    private final Storage storage;

    /**
     * Constructor for Parser.
     * @param taskList TaskList of Duke's tasks.
     * @param storage Storage instance of Duke.
     */
    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Returns a Command instance corresponding to the user input.
     * @param userInput User input command.
     * @return A command instance.
     * @throws DukeException Thrown when user command has a format issue.
     */
    public Command parseUserCommand(String userInput) throws DukeException {
        String cmd = userInput.split(" ")[0];

        try {
            return Command.createCommandFromInput(cmd, userInput, taskList, storage);
        } catch (DateTimeParseException e) {
            throw new DukeException("That's not a date! Use the format: yyyy-mm-dd");
        } catch (NumberFormatException e) {
            throw new DukeException("That's not a number! Go count your numbers before trying again.");
        } catch (IllegalArgumentException e) {
            throw new DukeException("Use a command I understand won't you?");
        }
    }

}
