package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Ui;

public class MarkCommand extends Command {
    private String command;
    private TaskList taskList;
    private Ui ui;

    public MarkCommand(String command, TaskList taskList, Ui ui) {
        this.command = command;
        this.taskList = taskList;
        this.ui = ui;
    }
     /*
     * Mark takes in a String comman and handles the command
     * by checking for out of bounds as well as invalid syntaxes 
     * and marks the corresponding task as completed
     * @throws DukeException if input is incorrect
     */
    @Override
    public boolean execute() throws DukeException {
        String[] inputs = command.split(" ");

        if (inputs.length == 2) {
            int ind = Integer.parseInt(inputs[1]) - 1;
            // If index falls out of bounds 
            if (ind >= taskList.size() || ind < 0) throw new DukeException("☹ OOPS!!! Invalid task number :(");

            taskList.get(ind).markCompleted();

            ui.printMarkedTask(taskList.get(ind));

        } else {
            throw new DukeException("Incorrect command: mark <valid task index>");
        }
        return false;
    }
}
