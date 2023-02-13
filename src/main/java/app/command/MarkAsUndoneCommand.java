package app.command;

import app.chatbot.Response;
import app.chatbot.Storage;
import app.task.TaskList;

public class MarkAsUndoneCommand extends Command {
    private static final String NUM_FORMAT_ERROR = "Please specify the task by its index number.";
    private static final String MISSING_TASK_ERROR = "Seems like this task doesn't exist.";
    private final String unmarkAtIndex;

    public MarkAsUndoneCommand(String index) {
        this.isExit = false;
        this.isSave = true;
        this.unmarkAtIndex = index;
    }

    /**
     * Unmarks the Done status of the Task at markAtIndex. Notifies the user if the Task was
     * already undone. Throws a message for formatting issues, or if the
     * corresponding Task to markAtIndex does not exist in the list.
     * @param tl
     * @param storage
     */
    @Override
    public Response execute(TaskList tl, Storage storage) {
        boolean alreadyMarked;
        try {
            alreadyMarked = tl.unmarkDone(unmarkAtIndex);
            String responseString;
            if (alreadyMarked) {
                responseString = tl.getTask(unmarkAtIndex).getDesc() + " is already undone!";
            } else {
                responseString = "Unmarked " + tl.getTask(unmarkAtIndex).getDesc() + ".";
            }
            return new Response(responseString, true);
        } catch (NumberFormatException e) {
            return new Response(NUM_FORMAT_ERROR, false);
        } catch (IndexOutOfBoundsException e) {
            return new Response(MISSING_TASK_ERROR, false);
        }
    }
}
