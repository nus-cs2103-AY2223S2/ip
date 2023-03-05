package app.command;

import java.util.List;

import app.chatbot.Response;
import app.chatbot.Storage;
import app.task.Task;
import app.task.TaskList;

public class ListCommand extends Command {
    private static final String NOTHING_TO_LIST_MESSAGE = "You don't have anything listed right now.";
    private static final String LIST_INTRO_MESSAGE = "Eh this is what you've written down so far:\n";
    public ListCommand() {
        this.isExit = false;
        this.isSave = false;
    }

    /**
     * Lists the Tasks in the TaskList. If there are no tasks, replies with
     * a different message instead.
     * <br>
     * Note that the tasks are numbered from 1 onwards. Order of tasks follows
     * the indexing of the TaskList.
     * @param tl
     * @param storage
     */
    @Override
    public Response execute(TaskList tl, Storage storage) {
        List<Task> listTasks = tl.getAllTasks();
        Response response = new Response(true);
        if (listTasks.isEmpty()) {
            response.addLine(NOTHING_TO_LIST_MESSAGE);
        } else {
            StringBuilder output = new StringBuilder(LIST_INTRO_MESSAGE);
            int taskIndex = 1;
            for (Task t : tl) {
                output.append(taskIndex)
                        .append(": ")
                        .append(t)
                        .append("\n");
                taskIndex++;
            }

            response.addLine(output.toString());
            response.addLine("You have " + listTasks.size() + " tasks listed.");
        }
        return response;
    }
}
