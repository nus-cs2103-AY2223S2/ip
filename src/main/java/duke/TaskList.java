package duke;
import java.util.*;

import duke.task.*;

/**
 * stores and handles edits to current session task list
 */
public class TaskList {
    private static ArrayList<Task> list;
    public TaskList() {
        this.list = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }
    public ArrayList<Task> getList() {
        return this.list;
    }
    public String showList() {
        String response = "";
        int j = 0;
        for (Task i: list) {
            j++;
            response = response + String.valueOf(j) + ". " + i + "\n";
        }
        return response;
    }
    /**
     * Marks task according to boolean
     *
     * @param i task to remove
     * @param b boolean whether it is to be marked or un mark
     */
    public String markTask(int i, boolean b) {
        int index = i - 1;
        list.get(index).markTask(b);
        return "Marked/Unmarked the task, task is in the state:\n" + "  " + list.get(index);
    }
    /**
     * add task to list
     *
     * @param task task to be added to list
     */
    public String addList(Task task) {
        list.add(task);
        return "added: " + task.getDescription() + "\n You have: " + list.size() + " task(s)\n";

    }
    /**
     * remeove task to list
     *
     * @param i task to be added to list
     */
    public String deleteTask(int i) {
        int index = i - 1;
        String response = "";
        response = "removed: " + list.get(index).toString() + "\n You have: " + (list.size() - 1) + " task(s)\n";
        list.remove(index);
        return response;
    }
    public String find(String word) {
        StringBuilder display = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            Task curr = this.list.get(i);
            if (curr.toString().toLowerCase().contains(word.toLowerCase())) {
                display.append((curr) + ". " + curr.getStatusIcon() + "\n");
            }
        }
        return display.toString();
    }
}
