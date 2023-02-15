package duke.helpers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import duke.tasks.*;

public class TaskList {
    private final ArrayList<Task> tasks;
    private final Storage db;
    private String message;

    public TaskList() throws IOException {
        // Loads saved file if it exists, else creates empty new file
        File savedTasks = new File("./tasks.txt");
        this.db = new Storage(savedTasks);
        this.tasks = new ArrayList<Task>(db.populateTasks());
    }

    public boolean isEmpty() {
        return tasks.size() == 0;
    }

    public String list() {
        String list = "";
        for (int i = 1; i <= tasks.size(); i++) {
            list += String.format("%d. %s\n", i, tasks.get(i-1));
        }
        return list;
    }

    public Task mark(int index) {
        Task task = tasks.get(index - 1);
        task.setDone();
        return task;
    }

    public Task unmark(int index) {
        Task task = tasks.get(index - 1);
        task.setUndone();
        return task;
    }

    public Task delete(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        return task;
    }

    public String add(Task task) {
        tasks.add(task);

        message = String.format("    %s\n", task);
        String s = tasks.size() == 1 ? "task" : "tasks";
        message += String.format("Now you have %d %s in the list.\n", tasks.size(), s);
        return message;
    }

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
            searchList += String.format("%d. %s\n", i, searchResults.get(i-1));
        }
        return searchList;
    }

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

        UI.printWithLines("Saving the following tasks: \n" + this.list());
        this.db.saveTasks(toWrite);
    }
}