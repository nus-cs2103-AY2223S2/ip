package duke.commands;

import duke.database.Database;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/** Represents a command to add a toDo task to the TaskList. */

public class AddToDoCommand extends Command {


    private final String task;

    /**
     * Represents a command to add a todo task to the TaskList.
     *
     * @param task Task to be done.
     */
    public AddToDoCommand(String task) {
        super();
        this.task = task;
    }

    /**
     * Executes the generated AddToDoCommand by adding a new todo task into the TaskList and gives a response
     * to the Ui.
     *
     * @param taskList taskList of Duke.
     * @param ui user interface object of Duke.
     * @param database database of Duke.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Database database) {
        ToDo newToDo = new ToDo(this.task);
        taskList.addTask(newToDo);
        ui.response(FRAME + "\n"
                + "Got it. I've added this task:" + "\n"
                + newToDo.getStatus() + "\n"
                + "Now you have " + taskList.length() + " tasks in the list" + "\n"
                + FRAME);
    }
}
