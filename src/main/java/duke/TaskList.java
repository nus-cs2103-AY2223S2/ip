package duke;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList(){
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public static ArrayList<Task> getList() {
        return tasks;
    }

    public static String markDone(int index) {
        tasks.get(index).markDone();
        return tasks.get(index).getTaskName();
    }

    public static String markUndone(int index) {
        tasks.get(index).markUndone();
        return tasks.get(index).getTaskName();
    }

    public static void addTask(Task task) {
        tasks.add(task);
    }

    public static Task remove(int index) {
        return tasks.remove(index);
    }

    public static void close() {
        tasks = null;
    }

}
