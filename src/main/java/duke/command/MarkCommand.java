package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.enums.Views;
import duke.task.Task;

/**
 * Command: Marks the Task as complete
 */
public class MarkCommand extends Command {
    private int[] taskNumbers;

    /**
     * Takes in the task number to mark as complete
     *
     * @param taskNumbers int index of task in the ArrayList
     * @throws DukeException
     */
    public MarkCommand(int... taskNumbers) throws DukeException {
        if (taskNumbers.length == 0) {
            throw new DukeException(Views.NO_INT_ERR_STRING);
        }
        assert taskNumbers.length != 0 : Views.NO_INT_ERR_STRING.str();
        this.taskNumbers = taskNumbers;
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
            int taskNum = taskNumbers[0];
            execute(tasks, storage, taskNum);
            Ui.showMarkDone(tasks, taskNum);
        } else {
            ArrayList<Task> printTasks = new ArrayList<Task>();
            for (int taskNum : taskNumbers) {
                execute(tasks, storage, taskNum);
                printTasks.add(tasks.get(taskNum));
            }
            Ui.showMarkDone(printTasks);
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
        tasks.get(taskNum).markAsDone();
        storage.save(tasks);
    }

    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param storage object required when command writes to file
     * @return returns the UI text instead of printing
     * @throws DukeException
     */
    public String executeString(TaskList tasks, Storage storage) throws DukeException {
        if (taskNumbers.length == 1) {
            int taskNum = taskNumbers[0];
            execute(tasks, storage, taskNum);
            return Ui.stringMarkDone(tasks, taskNum);
        } else {
            ArrayList<Task> printTasks = new ArrayList<>();
            for (int taskNum : taskNumbers) {
                execute(tasks, storage, taskNum);
                printTasks.add(tasks.get(taskNum));
            }
            return Ui.stringMarkDone(printTasks, true);
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
