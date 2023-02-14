package duke.command;

import java.util.ArrayList;
import java.util.Arrays;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.enums.Views;
import duke.task.Task;

/**
 * Command: Deletes the Task given
 */
public class DeleteCommand extends Command {
    private int[] taskNumbers;

    /**
     * Creates Delete Command
     *
     * @param taskNumbers int[] index of task in the ArrayList
     * @throws DukeException
     */
    public DeleteCommand(int... taskNumbers) throws DukeException {
        if (taskNumbers.length == 0) {
            throw new DukeException(Views.NO_INT_ERR_STRING);
        }
        assert taskNumbers.length != 0 : Views.NO_INT_ERR_STRING.str();
        this.taskNumbers = taskNumbers;
        Arrays.sort(this.taskNumbers);
    }

    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param storage object required when command writes to file
     * @throws DukeException
     */
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        if (taskNumbers.length == 1) {
            Task delTask = tasks.get(0);
            execute(tasks, storage, 0);
            Ui.showDel(delTask, tasks);
        } else {
            ArrayList<Task> printTasks = new ArrayList<Task>();
            for (int taskNum : taskNumbers) {
                printTasks.add(tasks.get(taskNum));
            }
            executeMultiple(tasks, storage);
            Ui.showDel(printTasks, tasks);
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
        tasks.remove(taskNum);
        storage.save(tasks);
    }

    /**
     * Executes the command privately, abstract from previous two executes
     *
     * @param tasks   TaskList object to get and set the list
     * @param storage object required when command writes to file
     * @param taskNum task's number to operate on
     * @throws DukeException
     */
    private void executeMultiple(TaskList tasks, Storage storage) throws DukeException {
        // Assume array is sorted in ascending order, remove delete the items one at the
        // time from the back, so that the correct order of items are being removed
        for (int i = taskNumbers.length - 1; i >= 0; i--) {
            int taskNum = taskNumbers[i];
            execute(tasks, storage, taskNum);
        }
    }

    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param storage object required when command writes to file
     * @return returns the UI text instead of print[]ing
     * @throws DukeException
     */
    public String executeString(TaskList tasks, Storage storage) throws DukeException {
        if (taskNumbers.length == 1) {
            Task delTask = tasks.get(0);
            execute(tasks, storage, 0);
            return Ui.stringDel(delTask, tasks);
        } else {
            ArrayList<Task> printTasks = new ArrayList<Task>();
            for (int taskNum : taskNumbers) {
                printTasks.add(tasks.get(taskNum));
            }
            execute(tasks, storage);
            return Ui.stringDel(printTasks, tasks, true);
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
