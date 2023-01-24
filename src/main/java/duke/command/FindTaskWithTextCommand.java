package duke.command;

import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class FindTaskWithTextCommand extends Command {
    private Ui ui;

    private TaskList taskList;

    public FindTaskWithTextCommand(String commandMessage, Ui ui, TaskList taskList) {
        super(commandMessage);
        this.ui = ui;
        this.taskList = taskList;
    }

    @Override
    public boolean execute() {
        int size = taskList.getSize();
        String searchString = commandMessage.split(" ", 2)[1];

        if (size == 0) {
            this.ui.replyEmptyTaskList();
        } else {
            this.ui.replySearchStart();

            for (int i = 1; i <= size; i++) {
                Task task = taskList.getTask(i);

                if (task.hasSubstring(searchString)) {
                    this.ui.replyTaskInfo(task);
                }
            }
        }

        return false;
    }
}
