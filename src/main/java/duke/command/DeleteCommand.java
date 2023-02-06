package duke.command;

import java.util.ArrayList;

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
     */
    public DeleteCommand(int[] taskNumbers) {
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
        assert taskNumbers.length != 0 : Views.NO_INT_ERR_STRING.eng();
        if (taskNumbers.length == 1) {
            Task delTask = tasks.get(0);
            execute(tasks, storage, 0);
            ui.showDel(delTask, tasks);
        } else {
            ArrayList<Task> printTasks = new ArrayList<Task>();
            for (int taskNum : taskNumbers) {
                printTasks.add(tasks.get(taskNum));
                execute(tasks, storage, taskNum);
            }
            ui.showDel(printTasks, tasks);
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
        assert taskNumbers.length != 0 : Views.NO_INT_ERR_STRING.eng();
        tasks.remove(taskNum);
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
            Task delTask = tasks.get(0);
            execute(tasks, storage, 0);
            return ui.stringDel(delTask, tasks);
        } else {
            ArrayList<Task> printTasks = new ArrayList<Task>();
            for (int taskNum : taskNumbers) {
                printTasks.add(tasks.get(taskNum));
                execute(tasks, storage, taskNum);
            }
            return ui.stringDel(printTasks, tasks, true);
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
