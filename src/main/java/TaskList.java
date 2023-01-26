import duke.task.Task;

import java.util.ArrayList;

/**
 * TaskList contains the task list
 * e.g., it has operations to add/delete tasks in the list
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public static Integer size() {
        return tasks.size();
    }

    public static void addTask(Task t) {
        tasks.add(t);
        TaskList.announceAdded(t);
    }

    public Task getTaskAtIndex(Integer i) {
        return tasks.get(i);
    }

    public void deleteTaskAtIndex(Integer i) {
        try {
            if (i < 0 || i >= tasks.size()){
                throw new DukeException("☹ OOPS!!! The number to delete is invalid.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        Task toDelete = this.getTaskAtIndex(i);
        tasks.remove(toDelete);
        TaskList.announceRemoved(toDelete);
    }
    public static void announceAdded(Task t) {
        System.out.println("Got it. I've added this task:");
        System.out.println("Now we have " + tasks.size() + " tasks in the list.");
    }

    public static void announceRemoved(Task t) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t.toString());
        System.out.println("Now we have " + tasks.size() + " tasks in the list.");
    }

    public void taskMarkedAtIndex(Integer i) {
        try {
            if (i < 0 || i >= this.size()){
                throw new DukeException("☹ OOPS!!! The number to mark is invalid.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        this.getTaskAtIndex(i).taskDone();
    }

    public void taskUnmarkedAtIndex(Integer i) {
        try {
            if (i < 0 || i >= this.size()){
                throw new DukeException("☹ OOPS!!! The number to mark is invalid.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        this.getTaskAtIndex(i).taskNotDone();
    }
    public void printTaskList() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = this.getTaskAtIndex(i);
            System.out.println((i+1) + ". " + task.toString());
        }
    }
}
