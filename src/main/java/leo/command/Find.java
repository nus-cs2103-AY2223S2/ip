package leo.command;

import leo.leoException.LeoException;
import leo.leoException.NoKeywordException;
import leo.leoException.NoTaskFoundException;
import leo.storage.Storage;
import leo.storage.Task;
import leo.storage.TaskList;
import leo.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class Find extends Command {

    public Find(Storage storage, String task) {
        super(storage, task);
        try {
            TaskList taskList = findTasks(storage, task);
            Ui.displayMessage(Ui.leoResponse("Here's the matching task(s):"));
            taskList.display();
        } catch (LeoException e) {
            Ui.displayMessage(Ui.leoResponse(e.getMessage()));
        }
    }

    public TaskList findTasks(Storage storage, String task) throws NoKeywordException, NoTaskFoundException {
        List<Task> foundList = new ArrayList<>();
        int dataLength = storage.getDataLength();
        if (dataLength == 0) {
            throw new NoTaskFoundException("There are no matching tasks!");
        }
        try {
            String keyword = task.substring(5);
            for (int i = 0; i < dataLength; i++) {
                Task t = storage.getTask(i);
                if (t.getTask().contains(keyword)) {
                    foundList.add(t);
                }
            }
            return new TaskList(foundList);
        } catch (Exception e) {
            throw new NoKeywordException("Oops! I do not know what I should be searching for...");
        }

    }

}
