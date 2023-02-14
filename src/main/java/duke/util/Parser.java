package duke.util;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;

/**
 * Responsible for interpreting user inputs.
 */
public class Parser {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Class constructor that takes in a Storage, TaskList and Ui
     * @param storage Interacts with the specified data file.
     * @param tasks Contains all current tasks.
     * @param ui Interacts with user.
     */
    public Parser(Storage storage, TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    public String getCommand(String[] userInput) {
        return userInput[0];
    }

    public String getCommandDetails(String[] userInput) throws DukeException {
        try {
            return userInput[1];
        } catch (Exception e) {
            throw new DukeException("You have entered a command with no details! Please try again.");
        }
    }

    public int getTaskNumber(String commandDetails) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(commandDetails);
            return taskNumber;
        } catch (Exception e) {
            throw new DukeException("You entered an invalid task number!");
        }
    }

    /**
     * Takes in an input from the user and executes the command.
     * @param userInput Input from user stored in a String array.
     * @return A Command corresponding to user input.
     * @throws DukeException If the user input is invalid.
     */
    public Command parse(String[] userInput) throws DukeException {
        switch (getCommand(userInput)) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            int taskNumberToMark = getTaskNumber(userInput[1]);
            return new MarkCommand(taskNumberToMark, true);
        case "unmark":
            int taskNumberToUnmark = getTaskNumber(getCommandDetails(userInput));
            return new MarkCommand(taskNumberToUnmark, false);
        case "todo":
            String todoDescription = getCommandDetails(userInput);
            if (todoDescription == null) {
                throw new DukeException("Enter a valid Todo!");
            } else {
                return new TodoCommand(todoDescription);
            }
        case "deadline":
            String[] deadlineDetails = getCommandDetails(userInput).split(" /by ", 2);
            String deadlineDescription = deadlineDetails[0];
            String deadline = deadlineDetails.length > 0 ? deadlineDetails[1] : null;
            if (deadline == null) {
                throw new DukeException("Enter a valid deadline!");
            }
            return new DeadlineCommand(deadlineDescription, deadline);
        case "event":
            String[] eventDetails = getCommandDetails(userInput).split(" /from ", 2);
            String eventDescription = eventDetails[0];
            String eventPeriod = eventDetails.length > 0 ? eventDetails[1] : null;

            if (eventPeriod == null) {
                throw new DukeException("Enter a valid event period!");
            }

            String[] splitEventPeriod = eventPeriod.split(" /to ");

            if (splitEventPeriod.length < 2) {
                throw new DukeException("Enter a valid event period!");
            }
            return new EventCommand(eventDescription, splitEventPeriod);
        case "delete":
            int taskNumber = getTaskNumber(getCommandDetails(userInput));
            return new DeleteCommand(taskNumber);
        case "find":
            return new FindCommand(getCommandDetails(userInput));
        default:
            throw new DukeException("Enter a valid task!");
        }
    }
}
