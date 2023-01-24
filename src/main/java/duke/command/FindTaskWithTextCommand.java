package duke.command;

import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command that search for tasks with keywords.
 *
 * @author wz2k
 */
public class FindTaskWithTextCommand extends Command {
    /** The medium which the chatbot uses to communicate */
    private Ui ui;

    /** The list of task maintained by the chatbot */
    private TaskList taskList;

    /**
     * Creates a command for listing tasks that matches the keywords.
     *
     * @param commandMessage User's input.
     * @param ui Communication medium.
     * @param taskList List of tasks.
     */
    public FindTaskWithTextCommand(String commandMessage, Ui ui, TaskList taskList) {
        super(commandMessage);
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Lists the matching tasks for the user and returns if the conversation with
     * the chatbot has ended.
     *
     * @return True if conversation has ended and false otherwise.
     */
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
