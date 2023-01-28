package app.command;

import app.chatbot.Storage;
import app.chatbot.Ui;
import app.task.Task;
import app.task.TaskList;

import java.util.List;

public class ListCommand extends Command {

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
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        List<Task> listTasks = tl.getAllTasks();
        if (listTasks.isEmpty()) {
            ui.reply("You don't have anything listed right now.");
        } else {
            StringBuilder output = new StringBuilder("Eh this is what you've written down so far:\n");
            for (int i = 0; i < listTasks.size(); i++) {
                output.append(i+1);
                output.append(": " + listTasks.get(i));
                output.append("\n");
            }
            ui.reply(output.toString());
            ui.reply("You have " + listTasks.size() + " tasks listed.");
        }
    }
}
