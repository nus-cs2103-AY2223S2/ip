package duke.command;

import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class ListTasksCommand extends Command {
    private Ui ui;

    private TaskList taskList;

    public ListTasksCommand(String commandMessage, Ui ui, TaskList taskList) {
        super(commandMessage);
        this.ui = ui;
        this.taskList = taskList;
    }

    @Override
    public boolean execute() {
        int size = this.taskList.getSize();

        if (size == 0) {
            this.ui.replyEmptyTaskList();
        } else {
            this.ui.replyTotalTasks(taskList);

            for (int i = 1; i <= size; i++) {
                Task task = this.taskList.getTask(i);
                this.ui.replyTaskInfo(task);
            }
        }

        return false;
    }
}
