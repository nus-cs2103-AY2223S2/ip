package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

enum commands {
    Deadline("deadline DESCRIPTION /by YYYY-MM-DDTHH:MM"),
    Event("event DESCRIPTION /from YYYY-MM-DDTHH:MM /to YYYY-MM-DDTHH:MM"),
    Todo("todo DESCRIPTION"),
    Find("find KEYWORD"),
    Mark("mark INDEX"),
    Unmark("unmark INDEX"),
    Delete("delete INDEX"),
    List("list"),
    Bye("bye"),
    Help("help");

    private String commandFormat;

    commands(String format) {
        commandFormat = format;
    }

    String getCommandFormat() {
        return commandFormat;
    }
}

/**
 * Represents a HelpCommand that implements the Command interface.
 */
public class HelpCommand implements Command {

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws DukeException {
        String toDisplay = "I see you asked for help? YOU GOT IT!\n"
                + "These are the available commands and their respective command formats:\n";
        for (commands c : commands.values()) {
            toDisplay += String.format("\n|%s| %s\n", c, c.getCommandFormat());
        }
        ui.displayMessage(toDisplay);
        return toDisplay;
    }
}
