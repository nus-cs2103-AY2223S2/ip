package duke.command;

import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command that lists the user's tasks.
 *
 * @author wz2k
 */
public class ListTasksCommand extends Command {
    /** The medium which the chatbot uses to communicate */
    private Ui ui;

    /** The list of task maintained by the chatbot */
    private TaskList taskList;

    /**
     * Creates a command for listing the user's tasks.
     *
     * @param commandMessage User's input.
     * @param ui Communication medium.
     * @param taskList List of tasks.
     */
    public ListTasksCommand(String commandMessage, Ui ui, TaskList taskList) {
        super(commandMessage);
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Lists the tasks for the user and returns if the conversation with
     * the chatbot has ended.
     *
     * @return True if conversation has ended and false otherwise.
     */
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
