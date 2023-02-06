package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Executable command to mark an existing task as completed.
 *
 * @author Guo-KeCheng
 */
public class MarkCommand extends Command {
    private final String command;
    private final TaskList taskList;
    private final Ui ui;

    /**
     * MarkCommand constructor
     *
     * @param command  Entire line of user input
     * @param taskList Existing taskList
     * @param ui       Shared Ui object
     */
    public MarkCommand(String command, TaskList taskList, Ui ui) {
        this.command = command;
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Mark corresponding task as completed
     * Checking for out of bounds as well as invalid syntax
     *
     * @throws DukeException if input is incorrect
     */
    @Override
    public String execute() throws DukeException {
        String[] inputs = command.split(" ");

        if (inputs.length == 2) {
            int ind = Integer.parseInt(inputs[1]) - 1;

            // If index falls out of bounds
            if (ind >= taskList.size() || ind < 0) {
                throw new DukeException("☹ OOPS!!! Invalid task number :(");
            }

            taskList.get(ind).markCompleted();

            return ui.printMarkedTask(taskList.get(ind));

        } else {
            throw new DukeException("Incorrect command: mark <valid task index>");
        }
    }
}
