package duke.commands;

import duke.Ui;
import duke.exceptions.TaskInitError;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

public class ToDoCmd extends Command {
    Task toDo;

    public ToDoCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
        this.lineInput = lineInput;
    }

    public void execute() {
        try { 
            this.toDo = ToDo.create(this.lineInput);
            taskList.add(this.toDo);
        } catch (TaskInitError e) {
            Ui.displayMsg("OOPS!!! " + e.getMessage());
        } 
        uiReply();
    };

    public void uiReply() {
        Ui.displayMsg("Got it. I've added this task:\n" + 
        Ui.indentString(this.toDo.toString(), 1) + "\n" +
        Ui.numTaskToString(taskList.countTasks()));
    };
}
