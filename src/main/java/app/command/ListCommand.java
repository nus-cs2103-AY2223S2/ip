package app.command;

import java.util.List;

import app.chatbot.Response;
import app.chatbot.Storage;
import app.chatbot.Ui;
import app.task.Task;
import app.task.TaskList;

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
    public String execute(TaskList tl, Ui ui, Storage storage) {
        List<Task> listTasks = tl.getAllTasks();
        if (listTasks.isEmpty()) {
            return new Response("You don't have anything listed right now.").toString();
        } else {
            StringBuilder output = new StringBuilder("Eh this is what you've written down so far:\n");
            for (int i = 0; i < listTasks.size(); i++) {
                output.append(i + 1)
                        .append(": ")
                        .append(listTasks.get(i))
                        .append('\n');
            }
            Response r = new Response(output.toString());
            r.addLine("You have " + listTasks.size() + " tasks listed.");
            return r.toString();
        }
    }
}
