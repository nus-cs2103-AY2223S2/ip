package app.command;

import app.chatbot.Response;
import app.chatbot.Storage;
import app.task.InvalidInputException;
import app.task.Task;
import app.task.TaskList;

public class DeleteCommand extends Command {
    private static final String INVALID_ARG_ERROR = "Specify a task by its task number. "
            + "Try 'list' or 'find' to get the number :)";
    private final String deleteAtIndex;

    /**
     * Takes in the USER-input index - this index refers to the task as
     * presented by the List command.
     * @param index user-input index.
     */
    public DeleteCommand(String index) {
        this.isExit = false;
        this.isSave = true;
        this.deleteAtIndex = index;
    }

    /**
     * Deletes a Task from the TaskList, and informs the user of the Task deleted.
     * @param tl
     * @param storage
     * @throws Exception
     */
    @Override
    public Response execute(TaskList tl, Storage storage) {
        Response response;
        try {
            response = new Response(true);
            int i = Integer.parseInt(deleteAtIndex) - 1;
            Task deletedTask = tl.deleteTask(i);
            response.addLine("Removed '" + deletedTask.getDesc()
                    + "' from the list. You now have " + tl.size() + " tasks left.");
        } catch (NumberFormatException e) {
            response = new Response(false);
            response.addLine(INVALID_ARG_ERROR);
        } catch (InvalidInputException e) {
            response = new Response(false);
            response.addLine(e.getMessage());
        }
        return response;
    }
}
