package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Represents a simple command which doesn't change task list.
 * This includes "list" and "help".
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
        } else if (this.commandName.equals("help")) {
            return ui.showHelp();
        } else if (this.commandName.equals("add")) {
            return ui.showAddHelp();
        }
        return "";
    }
}
