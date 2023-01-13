package duke.instruction;

import duke.task.TaskList;

/**
 * A ListCommand class that encapsulates the action of displaying all the tasks on the TaskList.
 */

public class ListCommand extends Command {
    /**
     * Displays all the tasks with their respective types and status.
     *
     * @param list The user TaskList that contains all the task to be manipulated
     */
    @Override
    public void run(TaskList list) {
        StringBuilder listContent = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < list.remainingTasks(); i++) {
            listContent.append(i + 1).append(".").append(list.getTask(i)).append("\n");
        }
        format.displayWithBar(String.valueOf(listContent));
    }
}
