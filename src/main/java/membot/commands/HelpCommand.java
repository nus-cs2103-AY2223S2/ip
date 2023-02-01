package membot.commands;

import membot.view.Printable;

/**
 * Represents a command that provides users help with using the application.
 */
public class HelpCommand extends Command {
    /**
     * Generates a <code>HelpCommand</code> object.
     *
     * @param ui A Printable object used for UI display.
     */
    protected HelpCommand(Printable ui) {
        super(ui);
        this.ui = ui;
    }

    /**
     * Prints out a list of useful commands for Membot.
     */
    @Override
    public void execute() {
        this.ui.println(
                "Welcome to Membot ! Here are some things you can do:",
                "",
                "help - view all available commands",
                "list - view all tasks",
                "find [X] - find a task using a keyword",
                "todo [X] - create a TODO task",
                "deadline [X] - create a DEADLINE task",
                "event [X] - create an EVENT task",
                "delete [X] - delete a task",
                "done [X] - mark a task as completed",
                "undone [X] - mark a task as incomplete",
                "bye - exit Membot"
        );
    }
}
