package model;

import java.util.ArrayList;

public class Task {

    private String title;
    private static final Task[] tasks = new Task[100];
    private static int count = 0;

    private Task(String title) {
        this.title = title;
    }

    public static Task create(String title) {
        Task task = new Task(title);
        Task.tasks[count] = task;
        Task.count++;
        return task;
    }

    public static String[] listAll() {
        ArrayList<String> tasks = new ArrayList<>();
        for (int i = 0; i < Task.count; ++i) {
            tasks.add(Task.tasks[i].toString());
        }
        return tasks.toArray(new String[Task.count]);
    }

    @Override
    public String toString() {
        return String.format("%s", this.title);
    }
}
