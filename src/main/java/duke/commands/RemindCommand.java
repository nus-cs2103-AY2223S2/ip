package duke.commands;

import java.util.ArrayList;

import duke.components.Storage;
import duke.components.TaskList;
import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * This is the RemindCommand class to represent remind commands passed to Duke.
 * Encapsulates the information needed to display upcoming tasks to the user.
 */
public class RemindCommand extends Command {
    private final int daysToLookAhead;
    /**
     * Creates a new RemindCommand object.
     *
     * @param tokens tokenized user input.
     */
    public RemindCommand(ArrayList<String> tokens) throws DukeException {
        super(tokens);
        if (tokens.size() != 2) {
            throw new DukeException(
                    "Invalid input received!"
                            + "\nRemind commands are in the form of: remind i "
                            + "\nwhere i is the number of days to look ahead for"
                            + "\n(only 1 whitespace allowed)"
            );
        }
        this.daysToLookAhead = Integer.parseInt(tokens.get(1));
        if (daysToLookAhead < 0) {
            throw new DukeException(
                    "Invalid number of days received!"
                    + "\nPlease input a positive number of days to look ahead for tasks to be reminded of!"
            );
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        StringBuilder out = new StringBuilder();
        ArrayList<Task> upcomingTasks = tasks.getUpcomingTasks(daysToLookAhead);
        if (upcomingTasks.size() == 0) {
            return "It looks like you have no upcoming unfinished tasks for this timeframe, yay!";
        }
        out.append("It looks like you have ").append(upcomingTasks.size()).append(" tasks upcoming:\n");
        for (Task task : upcomingTasks) {
            out.append(task).append("\n");
        }
        return out.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
