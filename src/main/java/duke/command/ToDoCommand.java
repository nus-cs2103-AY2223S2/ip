package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.ToDo;

/** A representation of the "todo" command in Duke. */
public class ToDoCommand extends Command {

    private final String NAME = "todo";
    private String title;

    /**
     * Initializes a todo command with a given title.
     * 
     * @param title The title of the Event to be created
     */
    public ToDoCommand(String title) {
        this.title = title;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        Task task = new ToDo(this.title);
        taskList.addTask(task);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass() != this.getClass()) {
            return false;
        }
        ToDoCommand cmd = (ToDoCommand) obj;
        return this.title.equals(cmd.title);
    }
}
