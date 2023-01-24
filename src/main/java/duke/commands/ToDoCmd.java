package duke.commands;

import duke.Ui;
import duke.exceptions.TaskInitError;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * Command class for 'todo' command keyword.
 * Creates a new ToDo task and adds it to the task list.
 * <p>
 * Command format: "todo <task_name>"
 */
public class ToDoCmd extends Command {
    private Task toDo;

    /**
     * Constructor method.
     * @param taskList Task list to add the ToDo task to
     * @param lineInput Command line input that the user entered
     */
    public ToDoCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
        this.lineInput = lineInput;
    }

    // Adds the ToDo task to the task list.
    public void execute() {
        try { 
            this.toDo = ToDo.create(this.lineInput);
            taskList.add(this.toDo);
        } catch (TaskInitError e) {
            Ui.displayMsg("OOPS!!! " + e.getMessage());
        } 
        uiReply();
    };

    // Acknowlege on UI that the Deadline task has been added.
    public void uiReply() {
        Ui.displayMsg("Got it. I've added this task:\n" 
                + Ui.indentString(this.toDo.toString(), 1)
                + "\n" + Ui.numTaskToString(taskList.countTasks()));
    };
}
