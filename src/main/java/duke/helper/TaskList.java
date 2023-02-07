package duke.helper;

import duke.task.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the tasklist.
     *
     * @param currTask the task to be added.
     * @return The string to be printed
     */
    public String addTask(Task currTask) {
        tasks.add(currTask);
        return String.format("Added task nya!\n  %s\n"
                + " Nyow you have %d tasks in the list nya!\n", currTask, tasks.size());
    }

    /**
     * Deletes a task from the tasklist.
     *
     * @param id, the index of task in the tasklist to delete.
     * @return The string to be printed.
     */
    public String deleteTask(int id) {
        return String.format("Removed task nya!\n  %s\n" +
                        " Nyow you have %d tasks in the list nya!\n",
                tasks.remove(id - 1), tasks.size());

    }

    /**
     * Marks a task from the tasklist.
     *
     * @param id, the index of task in the tasklist to mark.
     * @return The string to be printed.
     */
    public String markTask(int id) {
        Task currTask = tasks.get(id - 1);
        currTask.setDone();
        return String.format("Good job for doing your task nya!\n  %s\n", currTask);
    }

    public String unmarkTask(int id) {
        Task currTask = tasks.get(id - 1);
        currTask.setUndone();
        return String.format("Set your task to undone nya!\n  %s\n", currTask);
    }

    public String listTasks() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output += " " + (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return "Here are your tasks nya!\n" + output;
    }

    public String findTask(String input) {
        String output = "";
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(input)) {
                output += " " + (count + 1) + ". " + tasks.get(i) + "\n";
                count++;
            }
        }
        return "Here are your matched tasks nya!\n" + output;
    }
}
