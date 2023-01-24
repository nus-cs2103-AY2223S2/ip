package duke.command;

import duke.exception.DukeException;
import duke.storage.CommandHistory;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {
    private enum CommandType {
        BYE("bye", "Exit the program"),
        DEADLINE("deadline [description] /by [yyyy-mm-dd]", "Add a deadline event with its " +
                "deadline specified"),
        DELETE("delete [taskIndex]", "Delete the task specified by the given index"),
        EVENT("event [description] /by [yyyy-mm-dd] /from [yyyy-mm-dd]", "Add a deadline event " +
                "with its starting and ending date specified"),
        FIND("find [keyword]", "List all the events that matches the input keyword. " +
                "(case insensitive)"),
        HELP("help", "Show help menu"),
        LIST("list", "Display all tasks in the current Task List"),
        MARK("mark [taskIndex]", "Mark the task specified by the given index as done"),
        MASS_DELETE("massDelete", "Delete all the tasks that have been marked as done"),
        SEARCH("search [yyyy-mm-dd]", "List all the Deadline tasks and Event tasks that takes " +
                "place on the given day"),
        UNMARK("unmark [taskIndex]", "Mark the task specified by the given index as undone"),
        UPDATE("update [taskIndex] [description]", "Update the description of the task specified " +
                "by the given index to be the new description"),
        TODO("todo [description]", "Add a todo event");

        private final String command;
        private final String description;

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
        StringBuilder message = new StringBuilder();
        message.append("Here are the available commands and their respective function: \n\n");
        for (CommandType commandType : CommandType.values()) {
            message.append("- ").append(commandType.getCommand()).append(" : ")
                    .append(commandType.getDescription()).append("\n\n");
        }
        message.append("All the command keywords are case insensitive!");
        ui.appendResponse(message.toString());
    }
}
