package app.command;

import app.chatbot.Response;
import app.chatbot.Storage;
import app.chatbot.Ui;
import app.task.Task;
import app.task.TaskList;

public class DeleteCommand extends Command {
    private String deleteAtIndex;

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
     * @param ui
     * @param storage
     * @throws Exception
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) throws Exception {
        Response response = new Response();
        try {
            int i = Integer.valueOf(deleteAtIndex) - 1;
            Task deletedTask = tl.deleteTask(i);
            response.addLine("Removed " + deletedTask.getDesc()
                    + " from the list. You now have " + tl.size() + " tasks left.");
        } catch (NumberFormatException e) {
            throw e;
        }
        return response.toString();
    }
}
