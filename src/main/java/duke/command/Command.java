package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Represents a simple command which doesn't change task list.
 * This includes "bye" and "list".
 */
public class Command {
    protected String commandName;

    /**
     * Class Constructor.
     * @param command Name of command.
     */
    public Command(String command) {
        this.commandName = command;
    }

    /**
     * Execute command to show task list / exit program
     * @param tasks Current task list.
     * @param ui Ui to show task list.
     */
    public String execute(TaskList tasks, Ui ui) {
        if (this.commandName.equals("list")) {
            return ui.showList(tasks);
        }
        return "";
    }
}
