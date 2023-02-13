package chad.command;

import java.util.ArrayList;

import chad.parser.InputValidator;
import chad.storage.TaskList;
import chad.task.Task;

/**
 * Command to search task with specific keyword.
 */
public class FindCommand extends Command {

    private String request;

    /**
     * Constructor method to create find command.
     * @param request User's request to find duke.task.
     */
    public FindCommand(String request) {
        this.request = request;
    }

    /**
     * Execute the <code>Find</code> task.
     *
     * @param tasks the list to store new task.
     * @return Response after getting all tasks.
     */
    @Override
    public String execute(TaskList tasks) {
        String keyword = InputValidator.normaliseFindRequest(request);

        ArrayList<Task> result = tasks.find(keyword);

        // no task with enquired keyword in the task list
        if (result.size() == 0) {
            return "Sorry, I couldn't find what you want. :(";
        }

        StringBuilder list = new StringBuilder();
        for (Task task : result) {
            list.append(task.toString()).append("\n");
        }

        return list.toString();
    }
}
