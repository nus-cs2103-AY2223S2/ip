package app.command;

import app.chatbot.Response;
import app.chatbot.Storage;
import app.chatbot.Ui;
import app.task.TaskList;

public class MarkAsUndoneCommand extends Command {
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
     * @param ui
     * @param storage
     * @throws Exception
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) throws Exception {
        boolean alreadyMarked;
        try {
            alreadyMarked = tl.unmarkDone(unmarkAtIndex);
            if (alreadyMarked) {
                return new Response(tl.getTask(unmarkAtIndex).getDesc() + " is already undone!").toString();
            } else {
                return new Response("Unmarked " + tl.getTask(unmarkAtIndex).getDesc() + ".").toString();
            }
        } catch (NumberFormatException e) {
            throw new Exception("Please specify the task by its index number.");
        } catch (IndexOutOfBoundsException e) {
            throw new Exception("Seems like this task doesn't exist.");
        }
    }
}
