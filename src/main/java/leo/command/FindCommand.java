package leo.command;

import java.util.ArrayList;
import java.util.List;

import leo.leoexception.LeoException;
import leo.leoexception.NoKeywordException;
import leo.leoexception.NoTaskFoundException;
import leo.storage.Storage;
import leo.storage.Task;
import leo.storage.TaskList;
import leo.ui.Ui;

/**
 * Represents a find command input by user.
 */
public class FindCommand extends Command {

    /**
     * Constructor to create FindCommand object.
     *
     * @param storage Storage to store the task.
     * @param task Description of the task.
     */
    public FindCommand(Storage storage, String task) {
        super(storage, task);
        try {
            TaskList taskList = findTasks(storage, task);
            Ui.displayMessage(Ui.leoResponse("Here's the matching task(s):"));
            taskList.display();
        } catch (LeoException e) {
            Ui.displayMessage(Ui.leoResponse(e.getMessage()));
        }
    }

    /**
     * Returns a TaskList object containing Tasks that matches the keyword input by user.
     *
     * @param storage Storage containing data to be searched.
     * @param task Task containing keyword to use for search.
     * @return TaskList containing Tasks that matches keyword.
     * @throws NoKeywordException If there is no keyword input.
     * @throws NoTaskFoundException If there are no matching tasks.
     */
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
