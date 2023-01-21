package model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Task {

    private String title;
    private static final LinkedList<Task> tasks = new LinkedList<>();
    private TaskStatus status = TaskStatus.NEW;

    protected Task(String title) {
        this.title = title;

        Task.tasks.add(this);
    }

    public static boolean isIdValid(int id) {
        return id >= 1 && id <= Task.tasks.size();
    }

    public static void setStatusCompleted(int id) throws IndexOutOfBoundsException {
        if (!Task.isIdValid(id)) {
            throw new IndexOutOfBoundsException();
        }

        Task.tasks.get(id - 1).setStatus(TaskStatus.COMPLETED);
    }

    public static void setStatusNew(int id) throws IndexOutOfBoundsException {
        if (!Task.isIdValid(id)) {
            throw new IndexOutOfBoundsException();
        }

        Task.tasks.get(id - 1).setStatus(TaskStatus.NEW);
    }

    public static Task delete(int id) throws IndexOutOfBoundsException {
        if (!Task.isIdValid(id)) {
            throw new IndexOutOfBoundsException();
        }

        return Task.tasks.remove(id - 1);
    }

    public static Task deleteLast() throws IndexOutOfBoundsException {
        return Task.tasks.removeLast();
    }

    private void setStatus(TaskStatus status) {
        this.status = status;
    }

    public static String[] listAll() {
        ArrayList<String> tasks = new ArrayList<>();
        for (int i = 0; i < Task.tasks.size(); ++i) {
            tasks.add(String.format("%s", Task.tasks.get(i).toString()));
        }
        return tasks.toArray(new String[Task.tasks.size()]);
    }

    public static String listOne(int id) throws IndexOutOfBoundsException {
        if (!Task.isIdValid(id)) {
            throw new IndexOutOfBoundsException();
        }

        return Task.tasks.get(id - 1).toString();
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
