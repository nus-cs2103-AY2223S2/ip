package model;

import java.util.ArrayList;

public class Task {

    private String title;
    private static final Task[] tasks = new Task[100];
    private static int count = 0;
    private TaskStatus status = TaskStatus.NEW;

    public Task(String title) {
        this.title = title;

        Task.tasks[count] = this;
        Task.count++;
    }

    public static boolean isIdValid(int id) {
        return id >= 1 && id <= Task.count;
    }

    public static void setStatusCompleted(int id) throws IndexOutOfBoundsException {
        if (!Task.isIdValid(id)) {
            throw new IndexOutOfBoundsException();
        }

        Task.tasks[id - 1].setStatus(TaskStatus.COMPLETED);
    }

    public static void setStatusNew(int id) throws IndexOutOfBoundsException {
        if (!Task.isIdValid(id)) {
            throw new IndexOutOfBoundsException();
        }

        Task.tasks[id - 1].setStatus(TaskStatus.NEW);
    }

    private void setStatus(TaskStatus status) {
        this.status = status;
    }

    public static String[] listAll() {
        ArrayList<String> tasks = new ArrayList<>();
        for (int i = 0; i < Task.count; ++i) {
            tasks.add(String.format("%s", Task.tasks[i].toString()));
        }
        return tasks.toArray(new String[Task.count]);
    }

    public static String listOne(int id) throws IndexOutOfBoundsException {
        if (!Task.isIdValid(id)) {
            throw new IndexOutOfBoundsException();
        }

        return Task.tasks[id - 1].toString();
    }

    private String printStatus() {
        switch (this.status) {
            case NEW:
                return " ";
            case COMPLETED:
                return "X";
            default:
                return "?";
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.printStatus(), this.title);
    }
}

 enum TaskStatus {
    NEW,
    COMPLETED
}
