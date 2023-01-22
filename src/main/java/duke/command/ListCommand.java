package duke.command;

import duke.DukeException;
import duke.task.TaskList;

public class ListCommand extends Command {

    private final TaskList taskList;

    /**
     * Constructor for ListCommand.
     * Displays the tasks in the current task list.
     * @param taskList TaskList of Duke's tasks.
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public boolean execute() throws DukeException {
        System.out.println("Arii has retrieved your current tasks...");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, taskList.get(i));
        }
        return false;
    }
}
