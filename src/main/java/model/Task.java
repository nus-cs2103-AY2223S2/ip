package model;

import storage.Outputable;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedList;

abstract public class Task {
    protected static final String EMPTY = "~";
    private static LinkedList<Task> tasks = new LinkedList<>();

    private final String title;
    private TaskStatus status;

    protected Task(String title) {
        this.title = title;
        this.status = TaskStatus.NEW;
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
            // Should not reach here
            return "?";
        }
    }

    public static void save(Outputable out) throws IOException {
        Base64.Encoder e = Base64.getEncoder();
        StringBuilder sb = new StringBuilder();
        for (Task t : Task.tasks) {

            String s = String.format("%s | %s | %s | %s | %s | %s",
                    e.encodeToString(t.getTaskType().toString().getBytes(StandardCharsets.UTF_8)),
                    e.encodeToString(t.title.getBytes(StandardCharsets.UTF_8)),
                    e.encodeToString(t.status.toString().getBytes(StandardCharsets.UTF_8)),
                    e.encodeToString(t.getDeadline().getBytes(StandardCharsets.UTF_8)),
                    e.encodeToString(t.getStartDateTime().getBytes(StandardCharsets.UTF_8)),
                    e.encodeToString(t.getEndDateTime().getBytes(StandardCharsets.UTF_8)));
            sb.append(s).append("\n");
        }

        out.write(sb.toString());
    }

    public static void load(ArrayList<String> in) {
        Base64.Decoder d = Base64.getDecoder();
        for (String s : in) {
            String[] split = s.split(" \\| ");

            byte[] taskType = d.decode(split[0]);
            byte[] title = d.decode(split[1]);
            byte[] status = d.decode(split[2]);
            byte[] deadline = d.decode(split[3]);
            byte[] startDateTime = d.decode(split[4]);
            byte[] endDateTime = d.decode(split[5]);

            Task restoredTask = null;
            switch (TaskType.valueOf(new String(taskType, StandardCharsets.UTF_8))) {
            case TODO:
                restoredTask = new ToDo(new String(title, StandardCharsets.UTF_8));
                break;
            case DEADLINE:
                restoredTask = new Deadline(new String(title, StandardCharsets.UTF_8),
                        new String(deadline, StandardCharsets.UTF_8));
                break;
            case EVENT:
                restoredTask = new Event(new String(title, StandardCharsets.UTF_8),
                        new String(startDateTime, StandardCharsets.UTF_8),
                        new String(endDateTime, StandardCharsets.UTF_8));
                break;
            }

            restoredTask.setStatus(TaskStatus.valueOf(new String(status, StandardCharsets.UTF_8)));
        }
    }

    abstract public TaskType getTaskType();
    abstract public String getDeadline();
    abstract public String getStartDateTime();
    abstract public String getEndDateTime();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.printStatus(), this.title);
    }
}

 enum TaskStatus {
    NEW,
    COMPLETED
}

enum TaskType {
    TODO,
    DEADLINE,
    EVENT
}
