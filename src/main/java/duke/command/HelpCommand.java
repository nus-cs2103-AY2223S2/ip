package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.parser.ErrorMessage;
import duke.storage.CommandHistory;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Objects;

public class HelpCommand extends Command {
    private enum helpType {
        NORMAL, DATE, TIME
    }
    private final helpType type;

    public HelpCommand(String information) throws InvalidInputException {
        super();
        try {
            this.type = helpType.valueOf(information.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(ErrorMessage.INVALID_HELP_COMMAND_ERROR);
        }
    }

    private final static String HEADER = "Here are the available commands and their respective function:";
    private final static String PROMPT = "All the command keywords are case insensitive!";

    private final String VALID_DATE_TIME_FORMAT =
            "Here are all the valid date time format" +
            "MMM d yyyy H:mm, MMM d yyyy HHmm\n" +
                    "MMM d yyyy HH:mm, yyyy-MM-d H:mm\n" +
                    "yyyy-MM-d HHmm, yyyy-MM-d HH:mm\n" +
                    "d/MM/yyyy H:mm, d/MM/yyyy HHmm\n" +
                    "d/MM/yyyy HH:mm, yyyy/MM/d H:mm\n" +
                    "yyyy/MM/d HHmm, yyyy/MM/d HH:mm\n" +
                    "yyyy/MM/d'T'Hm, yyyy/MM/d'T'H:mm\n" +
                    "yyyy-MM-d H:mm, yyyy-MM-d HHmm\n" +
                    "yyyy-MM-d HH:mm, d MMM yyyy H:mm\n" +
                    "d MMM yyyy HHmm, d MMM yyyy HH:mm\n" +
                    "MMM d, yyyy H:mm, MMM d, yyyy HHmm\n" +
                    "MMM d, yyyy HH:mm, d-MM-yyyy H:mm";

    private final String VALID_DATE_FORMAT =
            "Here are all the valid date time format" +
                    "MMM dd yyyy, yyyy-MM-dd\n" +
                    "dd/MM/yyyy, yyyy/MM/dd\n" +
                    "dd MMM yyyy, MMM dd, yyyy\n" +
                    "dd-mm-yyyy";

    /**
     * Enum to represent the different types of commands supported by the application
     */
    private enum CommandType {
        BYE("bye", "Exit the program"),
        DEADLINE("deadline [description] /by [date time]", "Add a deadline event with its " +
                "deadline specified, type \"help time\" to check all the available date format"),
        DELETE("delete [taskIndex]", "Delete the task specified by the given index"),
        EVENT("event [description] /by [date time] /from [date time]", "Add a deadline event " +
                "with its starting and ending date specified, type \"help time\" " +
                "to check all the available date format"),
        FIND("find [keyword]", "List all the events that matches the input keyword. " +
                "(case insensitive)"),
        Free("free","Find the next free date in the next month"),
        HELP("help", "Show help menu"),
        LIST("list", "Display all tasks in the current Task List"),
        MARK("mark [taskIndex]", "Mark the task specified by the given index as done"),
        MASS_DELETE("massDelete", "Delete all the tasks that have been marked as done"),
        VIEW("view [date]", "List all the Deadline tasks and Event tasks that takes " +
                "place on the given day, type \"help date\" to check all the available date format"),
        UNMARK("unmark [taskIndex]", "Mark the task specified by the given index as undone"),
        UPDATE("update [taskIndex] [description]", "Update the description of the task specified " +
                "by the given index to be the new description"),
        TODO("todo [description]", "Add a todo event");

        private final String command;
        private final String description;

        /**
         * Constructor for the CommandType enum
         *
         * @param command     the command string that the user inputs
         * @param description the description of the command
         */
        CommandType(String command, String description) {
            this.command = command;
            this.description = description;
        }

        /**
         * Gets the command string.
         *
         * @return the command string
         */
        public String getCommand() {
            return command;
        }

        /**
         * Gets the command description.
         *
         * @return the command description
         */
        public String getDescription() {
            return description;
        }
    }

    /**
     * Displays the available commands and their respective function.
     *
     * @param tasks The user TaskList that contains all the task to be manipulated
     * @param ui The ui Object used to display information
     * @param storage The Storage Object used to save and load the TaskList
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, CommandHistory commandHistory) throws DukeException {
        // Initialize a StringBuilder to store the help message
        if (type == helpType.NORMAL) {
            StringBuilder message = new StringBuilder();
            message.append(HEADER).append("\n\n");

            // Iterate through the CommandType enum and append the command and its description to the message
            for (CommandType commandType : CommandType.values()) {
                message.append("- ").append(commandType.getCommand()).append(" : ")
                        .append(commandType.getDescription()).append("\n\n");
            }

            message.append(PROMPT);
            // Send the message to the UI
            ui.appendResponse(message.toString());
        } if (type == helpType.DATE) {
            ui.appendResponse(VALID_DATE_FORMAT);
        } else if (type == helpType.TIME) {
            ui.appendResponse(VALID_DATE_TIME_FORMAT);
        }
    }
}
