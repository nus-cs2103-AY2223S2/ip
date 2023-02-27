package command;

import java.util.Iterator;

import task.Task;
import task.TaskList;

/**
 * Command to list all the latest recorded tasks of the current chat session.
 */
public class ListCommand extends Command {
    /**
     * Lists all tasks currently recorded in the chat session.
     * @param tasks The existing task list.
     * @return The return status of the result from executing this command in the form of a text message.
     */
    @Override
    public String execute(TaskList tasks) {
        Iterator<Task> tasksIterator = tasks.list().iterator();

        if (!tasksIterator.hasNext()) {
            return "There are currently no tasks in your list...";
        }

        StringBuilder response = new StringBuilder();

        response.append("Here are the tasks in your list:\n\n");

        int taskNo = 1;
        while (tasksIterator.hasNext()) {
            Task task = tasksIterator.next();
            response.append(taskNo + ". " + task + "\n");
            taskNo += 1;
        }

        return response.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String undo(TaskList tasks) {
        /* Just a placeholder. undo is not applicable here. */
        return null;
    }

    /**
     * Determines if the current command is an exit command.
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
