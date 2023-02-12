package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.enums.Views;
import duke.task.Task;

/**
 * Command: mark the item as undone
 */
public class UnmarkCommand extends Command {
    private int[] taskNumbers;

    /**
     * Creates the command to unmark task as done
     *
     * @param taskNumbers int[] index of task in the ArrayList
     */
    public UnmarkCommand(int... taskNumbers) {
        assert taskNumbers.length != 0 : Views.NO_INT_ERR_STRING.str();
        this.taskNumbers = taskNumbers;
    }

    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param ui      object to reply to user after the command has executed
     * @param storage object required when command writes to file
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNumbers.length == 1) {
            int taskNum = taskNumbers[0];
            execute(tasks, storage, taskNum);
            ui.showUnmarkDone(tasks, taskNum);
        } else {
            ArrayList<Task> printTasks = new ArrayList<>();
            for (int taskNum : taskNumbers) {
                execute(tasks, storage, taskNum);
                printTasks.add(tasks.get(taskNum));
            }
            ui.showUnmarkDone(printTasks);
        }
    }

    /**
     * Executes the command privately, abstract from previous two executes
     *
     * @param tasks   TaskList object to get and set the list
     * @param storage object required when command writes to file
     * @param taskNum task's number to operate on
     * @throws DukeException
     */
    private void execute(TaskList tasks, Storage storage, int taskNum) throws DukeException {
        tasks.get(taskNum).markAsUndone();
        storage.save(tasks);
    }

    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param ui      object to reply to user after the command has executed
     * @param storage object required when command writes to file
     * @return returns the UI text instead of print[]ing
     * @throws DukeException
     */
    public String executeString(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNumbers.length == 1) {
            int taskNum = taskNumbers[0];
            execute(tasks, storage, taskNum);
            return ui.stringUnmarkDone(tasks, taskNum);
        } else {
            ArrayList<Task> printTasks = new ArrayList<>();
            for (int taskNum : taskNumbers) {
                execute(tasks, storage, taskNum);
                printTasks.add(tasks.get(taskNum));
            }
            return ui.stringUnmarkDone(printTasks, true);
        }
    }

    /**
     * Checks if this command will exit the program
     *
     * @return boolean True if the command will exit the program
     */
    public boolean isExit() {
        return false;
    }
}
