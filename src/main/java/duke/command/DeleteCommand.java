package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Executable command to delete specific task.
 *
 * @author Guo-KeCheng
 */
public class DeleteCommand extends Command {
    private final String command;
    private final TaskList taskList;
    private final Ui ui;


    /**
     * DeleteCommand constructor
     *
     * @param command  Entire line of user input
     * @param taskList Existing taskList
     * @param ui       Shared Ui object
     */
    public DeleteCommand(String command, TaskList taskList, Ui ui) {
        this.command = command;
        this.taskList = taskList;
        this.ui = ui;
    }

    /*
     * delete task by removing the Task at the corresponding index
     * throws exception for wrong syntax and invalid task number
     */
    @Override
    public String execute() throws DukeException {
        String[] inputs = command.split(" ");
        if (inputs.length == 2) {
            
            int ind = Integer.parseInt(inputs[1]) - 1;
            if (ind >= taskList.size() || ind < 0) {
                throw new DukeException("☹ OOPS!!! Invalid task number :(");
            }

            taskList.remove(ind);

            return ui.printDeletedTask(taskList.get(ind));

        } else {
            throw new DukeException("Incorrect command: delete <valid task index>");
        }

    }
}
