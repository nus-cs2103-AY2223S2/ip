package Nerd.Commands;

import java.time.LocalDate;

import Nerd.entities.Deadline;
import Nerd.entities.Event;
import Nerd.entities.Task;
import Nerd.entities.TaskList;
import Nerd.Ui.Ui;

/**
 * Represents the Duke.Commands.SearchCommand of the Chat bot.
 */
public class SearchDateCommand extends Command {
    private final String date;

    /**
     * Constructor of search date commands
     *
     * @param date The description of the task.
     */
    public SearchDateCommand(String date) {
        this.date = date;
    }

    /**
     * Overridden processCommand method from the abstract class Command.
     * Processes the command for a SearchCommand.
     *
     * @param list The TaskList object that stores Tasks.
     * @param ui   User interface of the Chat bot.
     * @return Tasks that associate with the given date.
     */
    @Override
    public String processCommand(TaskList list, Ui ui) {
        LocalDate now = LocalDate.parse(date.trim());
        String output = ui.printSearchDate(now.toString(), list);
        return output;
    }
}
