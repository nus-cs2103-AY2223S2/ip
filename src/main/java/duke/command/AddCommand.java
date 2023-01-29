package duke.command;

import duke.task.Task;

import duke.tasklist.TaskList;

public class AddCommand extends Command {

    private final Task task;
    private final String taskType;

    public AddCommand(Task task, String taskType) {
        super(false);
        this.task = task;
        this.taskType = taskType;

    }

    @Override
    public void execute(TaskList taskList) {
        taskList.addTask(this.task, this.taskType);
    }

}
