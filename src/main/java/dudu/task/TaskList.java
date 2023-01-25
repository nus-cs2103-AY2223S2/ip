package dudu.task;

import dudu.exception.DuduException;
import dudu.exception.TaskNumRangeException;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;
    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) throws DuduException {
        this.list = list;
    }

    public ArrayList<Task> addTask(Task task) {
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println(getTotalTask());
        return list;
    }

    public String getTotalTask() {
        String secondHalf;
        if (list.size() <= 1) {
            secondHalf = " task in the list.";
        } else {
            secondHalf = " tasks in the list.";
        }
        return "Now you have " + list.size() + secondHalf;
    }
    public Task getTask(int index) throws TaskNumRangeException {
        if (index >= list.size() || index < 0) {
            throw new TaskNumRangeException(String.valueOf(index));
        }
        return list.get(index);
    }

    public ArrayList<Task> getList() {
        return list;
    }
    public ArrayList<Task> delete(int index) {
        list.remove(index);
        return list;
    }
    public void printList() {
        if (list.size() == 0) {
            System.out.println("There is no task in your list");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                Task currTask = list.get(i);
                System.out.println(i + 1 + "." + currTask);
            }
        }
    }
}
