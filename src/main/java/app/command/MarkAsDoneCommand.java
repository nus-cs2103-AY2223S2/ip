package app.command;

import app.chatbot.Response;
import app.chatbot.Storage;
import app.task.TaskList;

public class MarkAsDoneCommand extends Command {
    private static final String NUM_FORMAT_ERROR = "Please specify the task by its index number.";
    private static final String MISSING_TASK_ERROR = "Seems like this task doesn't exist.";
    private final String markAtIndex;

    public MarkAsDoneCommand(String index) {
        this.isExit = false;
        this.isSave = true;
        this.markAtIndex = index;
    }

    /**
     * Marks the Task at markAtIndex as Done. Notifies the user if the Task was
     * already previously done. Throws a message for formatting issues, or if the
     * corresponding Task to markAtIndex does not exist in the list.
     * @param tl
     * @param storage
     */
    @Override
    public Response execute(TaskList tl, Storage storage)  {
        boolean isAlreadyMarked;
        try {
            isAlreadyMarked = tl.markAsDone(markAtIndex);
            if (isAlreadyMarked) {
                return new Response("'" + tl.getTask(markAtIndex).getDesc()
                        + "' already marked as done!", false);
            } else {
                return new Response("Marked '" + tl.getTask(markAtIndex).getDesc() + "' as done!", true);
            }
        } catch (NumberFormatException e) {
            return new Response(NUM_FORMAT_ERROR, false);
        } catch (IndexOutOfBoundsException e) {
            return new Response(MISSING_TASK_ERROR, false);
        }
    }
}
