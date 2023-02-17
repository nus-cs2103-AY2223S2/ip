package duke.helpers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import duke.tasks.Task;

/**
 * Stores array of Tasks and handles any manipulation of tasks in array.
 *
 * @author jengoc415
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private final Storage db;
    private String message;

    /**
     * Constructor to load previously saved tasks if any.
     * Else creates a new blank .txt file.
     *
     * @throws IOException faulty file input
     */
    public TaskList() throws IOException {
        // Loads saved file if it exists, else creates empty new file
        File savedTasks = new File("./tasks.txt");
        this.db = new Storage(savedTasks);
        this.tasks = db.populateTasks();
    }

    /**
     * Checks if taskList is currently empty.
     *
     * @return true if empty, else false.
     */
    public boolean isEmpty() {
        return tasks.size() == 0;
    }

    /**
     * List out all current tasks with their completion status.
     *
     * @return Paragraph list of all tasks
     */
    public String list() {
        String list = "";
        for (int i = 1; i <= tasks.size(); i++) {
            list += String.format("%d. %s\n", i, tasks.get(i - 1));
        }
        return list;
    }

    /**
     * Marks task as complete.
     *
     * @param index Index of task to mark as completed.
     * @return Task that was marked as completed.
     */
    public Task mark(int index) {
        assert index < tasks.size() : "Index over bound";
        Task task = tasks.get(index - 1);
        task.setDone();
        return task;
    }

    /**
     * Marks task as incomplete.
     *
     * @param index Index of task to mark as incomplete.
     * @return Task that was marked as incomplete.
     */
    public Task unmark(int index) {
        assert index < tasks.size() : "Index over bound";
        Task task = tasks.get(index - 1);
        task.setUndone();
        return task;
    }

    /**
     * Deletes task.
     *
     * @param index Index of task to be deleted.
     *              All subsequent tasks index will be moved up.
     * @return Task that was deleted.
     */
    public Task delete(int index) {
        assert index < tasks.size() : "Index over bound";
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        return task;
    }

    /**
     * Add task (todo, deadline, event) to list of tasks.
     *
     * @param task task to be added.
     * @return Task to be added as well as successful prompt.
     */
    public String add(Task task) {
        tasks.add(task);

        message = String.format("    %s\n", task);
        String s = tasks.size() == 1 ? "task" : "tasks";
        message += String.format("Now you have %d %s in the list.\n", tasks.size(), s);
        return message;
    }


    /**
     * Find all tasks that contains keyword provided.
     *
     * @param keyword Keyed in by user
     * @return List of tasks that contains keyword.
     */
    public String find(String keyword) {
        ArrayList<Task> searchResults = new ArrayList<>();
        for (Task task : tasks) {
            String desc = task.getDescription();
            if (desc.contains(keyword)) {
                searchResults.add(task);
            }
        }

        if (searchResults.size() == 0) {
            return "There are no matching tasks in your list.\n";
        }

        String searchList = "Here are the matching tasks in your list:\n";
        for (int i = 1; i <= searchResults.size(); i++) {
            searchList += String.format("%d. %s\n", i, searchResults.get(i - 1));
        }
        return searchList;
    }

    /**
     * Encodes tasks into a paragraph of text.
     * Encoded text is passed to storage to save
     * into local hard drive.
     */
    public void save() {
        String toWrite = "";
        String instr;
        String extendedInstr;
        int completed;
        Task task;

        for (int i = 0; i < tasks.size(); i++) {
            task = tasks.get(i);
            instr = task.getInstruction();
            completed = task.isCompleted() ? 1 : 0;
            extendedInstr = String.format("%d %s", completed, instr);
            toWrite += extendedInstr + "\n";
        }

        try {
            this.db.saveTasks(toWrite);
        } catch ( IOException e ) {
            System.out.println("error saving tasks");
        }

    }
}
