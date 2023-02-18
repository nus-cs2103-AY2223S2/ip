package botanic.command;

import java.time.LocalDate;

import botanic.gui.Gui;
import botanic.storage.Storage;
import botanic.task.TaskList;

/**
 * Encapsulates the related fields and behavior
 * of a command to find a task with the given date.
 */
public class FindDateCommand extends Command {
    private LocalDate dateToFind;

    /**
     * Instantiates FlexFindCommand.
     *
     * @param dateToFind The date to search for in the list of tasks.
     */
    public FindDateCommand(LocalDate dateToFind) {
        this.dateToFind = dateToFind;
    }

    /**
     * Finds (case-insensitive) the tasks with names that matches the given keyword exactly.
     *
     * @param tasks The ArrayList of tasks.
     * @param storage The class that reads and write program data to hard drive.
     * @param gui The class that handles interaction with the users.
     * @return A string message to signify the success or failure of task executed.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Gui gui) {
        return tasks.findDate(this.dateToFind);
    }
}
