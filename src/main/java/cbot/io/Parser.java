package cbot.io;

import java.time.format.DateTimeParseException;

import cbot.command.BadInputException;
import cbot.command.Command;
import cbot.command.PoorInputException;
import cbot.task.Task;
import cbot.task.TaskList;

/**
 * Handles much of the parsing of user inputs.
 */
public class Parser {
    private Command command;
    private String input;

    /**
     * Constructs a new instance to process the user's current input. Attempts to recognize the Command,
     * and the rest of the input body.
     *
     * @param input The user's input.
     * @throws PoorInputException If the input command is not recognized.
     * @see Command
     */
    public Parser(String input)
            throws PoorInputException {
        if (input.contains(Task.SEP)) {
            throw new BadInputException("Please avoid using: \"" + Task.SEP + "\"");
        }

        boolean matchFound = false;

        for (Command c : Command.values()) {
            matchFound = c.matches(input);

            if (matchFound) {
                this.command = c;
                this.input = input.trim();
                break;
            }
        }

        if (!matchFound) {
            throw new PoorInputException("Sorry, I don't recognize that command :<");
        }
    }

    /**
     * Returns true if the stored Command is the BYE Command.
     *
     * @return true if the stored Command is BYE.
     */
    public boolean isBye() {
        return (this.command == Command.BYE);
    }

    /**
     * Returns true if the stored Command requires the task list to be saved.
     *
     * @return Whether the task list needs to be saved after the Command.
     */
    public boolean needSave() {
        return this.command.needSave();
    }

    /**
     * Processes the Command and the rest of the input. If needed, the TaskList is modified and the UI is given
     * outputs to print.
     *
     * @param tl The current list of tasks.
     * @return The String response to the command.
     * @throws PoorInputException If the input text is improper or erroneous.
     * @throws DateTimeParseException If some provided datetime is not in a recognized format.
     */
    public String respond(TaskList tl)
            throws PoorInputException, DateTimeParseException {
        return this.command.runCommand(tl, this.input);
    }
}
