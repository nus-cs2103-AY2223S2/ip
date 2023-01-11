package duke.instruction;

import duke.customization.DisplayFormat;
import duke.exception.GeneralDukeException;
import duke.task.TaskList;
import duke.task.TodoTask;

public class AddTaskInstruction extends GeneralDukeInstruction {
    private final String taskInfo;
    private static final DisplayFormat format = new DisplayFormat(50, 4);

    public AddTaskInstruction(String taskInfo) {
        this.taskInfo = taskInfo;
    }

    @Override
    public void run(TaskList list) throws GeneralDukeException {
        TodoTask newTask = new TodoTask(taskInfo);
        list.addTask(newTask);
        format.displayWithBar("added : " + this.taskInfo);
    }
}
