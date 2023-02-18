package lulu;

import lulu.task.Deadline;
import lulu.task.Event;
import lulu.task.Task;
import lulu.task.Todo;

import java.util.ArrayList;

/**
 * Represents a TaskList that tracks the users' tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void add(Task t) {
        this.list.add(t);
    }

    public void remove(int taskNumber) {
        assert (taskNumber > 0);
        list.remove(taskNumber - 1);
    }

    public int getSize() {
        return this.list.size();
    }

    public Task get(int index) {
        assert (index >= 0);
        return this.list.get(index);
    }

    public String getRecentTaskDescription() {
        assert (list.size() - 1 >= 0);
        return this.list.get(list.size() - 1).toString();
    }

    public String getTaskDescription(int taskNumber) {
        assert (taskNumber > 0);
        return this.list.get(taskNumber - 1).toString();
    }

    public void markTask(int taskNumber) {
        assert (taskNumber > 0);
        this.list.get(taskNumber - 1).markAsDone();
    }

    public void unmarkTask(int taskNumber) {
        assert (taskNumber > 0);
        list.get(taskNumber - 1).markAsUndone();
    }

    /**
     * Returns a new TaskList.
     *
     * @param description the description of the tasks that the user wishes to find
     * @return a new TaskList, with descriptions matching the input
     */
    public TaskList find(String description) {
        TaskList tasks = new TaskList();
        this.list.forEach((task) -> {
            if (task.containsDescription(description)) {
                tasks.add(task);
            }
        });
        return tasks;
    }

    /**
     * Returns a string to display the TaskList, starting from 1., 2., 3.
     *
     * @return a String that displays the TaskList in a list format
     */
    public String printList() {
        return this.list.stream()
                .map(task -> String.format("\n%d. %s", list.indexOf(task) + 1, task.toString()))
                .reduce("", String::concat);
    }

    public void update(int taskNumber, String updateText) {
        list.get(taskNumber - 1).update(updateText);
    }

    /**
     * This method loads tasks into the TaskList, provided it follows a given convention.
     * The convention must be as follows "X`i`Y",
     * where X is the type of task, todo, event or deadline
     * i is whether the task has been completed, 0 or 1
     * Y is the task description, additional description can be separated by `.
     *
     * @param s the task to be loaded
     */
    public void load(String s) {
        String[] command = s.split("`");
        assert (command[0] == "D" || command[0] == "E" || command[0] == "T");
        switch (command[0]) {
        case "D":
            this.add(new Deadline(command[2], command[3]));
            if (Integer.valueOf(command[1]) == 1) {
                list.get(list.size() - 1).markAsDone();
            }
            break;
        case "E":
            this.add(new Event(command[2], command[3], command[4]));
            if (Integer.valueOf(command[1]) == 1) {
                list.get(list.size() - 1).markAsDone();
            }
            break;
        case "T":
            this.add(new Todo(command[2]));
            if (Integer.valueOf(command[1]) == 1) {
                list.get(list.size() - 1).markAsDone();
            }
            break;
        }
    }
}
