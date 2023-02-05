package app.command;

import app.chatbot.Response;
import app.chatbot.Storage;
import app.chatbot.Ui;
import app.task.TaskList;

public class MarkAsDoneCommand extends Command {
    private String markAtIndex;

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
     * @param ui
     * @param storage
     * @throws Exception
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) throws Exception {
        boolean alreadyMarked;
        try {
            int i = Integer.parseInt(this.markAtIndex) - 1;
            alreadyMarked = tl.markAsDone(i);
            if (alreadyMarked) {
                return new Response(tl.getTask(i).getDesc() + " already marked as done!").toString();
            } else {
                return new Response("Marked " + tl.getTask(i).getDesc() + " as done!").toString();
            }
        } catch (NumberFormatException e) {
            throw new Exception("Please specify the task by its index number.");
        } catch (IndexOutOfBoundsException e) {
            throw new Exception("Seems like this task doesn't exist.");
        }
    }
}
