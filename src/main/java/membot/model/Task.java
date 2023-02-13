package membot.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import membot.storage.Outputable;

/**
 * Represents a <code>Task</code> object.
 */
public abstract class Task {
    protected static final String EMPTY = "~";
    private static LinkedList<Task> tasks = new LinkedList<>();

    private final String title;
    private TaskStatus status;

    /**
     * Generates a <code>Task</code> object.
     *
     * @param title The title of the task to be completed.
     */
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

    /**
     * Deletes a <code>Task</code> from the list of tasks with its <code>id</code>.
     *
     * @param id The <code>id</code> of the <code>Task</code> to be deleted.
     * @return The deleted <code>Task</code> object.
     * @throws IndexOutOfBoundsException If the <code>id</code> provided is not within range.
     */
    public static Task delete(int id) throws IndexOutOfBoundsException {
        if (!Task.isIdValid(id)) {
            throw new IndexOutOfBoundsException();
        }

        return Task.tasks.remove(id - 1);
    }

    /**
     * Deletes the last <code>Task</code> from the list.
     * @return The deleted <code>Task</code> object.
     * @throws IndexOutOfBoundsException If the list of tasks is empty.
     */
    public static Task deleteLast() throws NoSuchElementException {
        return Task.tasks.removeLast();
    }

    private void setStatus(TaskStatus status) {
        this.status = status;
    }

    /**
     * Retrieves all <code>Task</code> in the task list and returns their
     * titles as a string array.
     *
     * @return A string array of all the <code>Task</code> titles.
     */
    public static String[] listAll() {
        return Task.tasks.stream().map(Task::toString).toArray(String[]::new);
    }

    /**
     * Retrieves a <code>Task</code> title via its <code>id</code>.
     *
     * @param id The <code>id</code> of the <code>Task</code> to be retrieved.
     * @return The title of the specified <code>Task</code>.
     * @throws IndexOutOfBoundsException If the <code>id</code> is invalid.
     */
    public static String listOne(int id) throws IndexOutOfBoundsException {
        if (!Task.isIdValid(id)) {
            throw new IndexOutOfBoundsException();
        }

        return Task.tasks.get(id - 1).toString();
    }

    /**
     * Prints out the <code>Task</code> status.
     *
     * @return The <code>Task</code> status.
     */
    public String printStatus() {
        switch (this.status) {
        case NEW:
            return " ";
        case COMPLETED:
            return "X";
        default:
            assert false : "Available Task status are: NEW, COMPLETED";
        }

        return "?";
    }

    /**
     * Writes the lists of <code>Task</code> objects to an <code>Outputable</code>.
     *
     * @param out An <code>Outputable</code> object that handles saving of <code>Task</code> objects.
     * @throws IOException If some sort of IO error occurs during the process of writing.
     */
    public static void save(Outputable out) throws IOException {
        Base64.Encoder encoder = Base64.getEncoder();
        StringBuilder sb = new StringBuilder();
        for (Task t : Task.tasks) {
            String s = String.format("%s | %s | %s | %s | %s | %s",
                    encoder.encodeToString(t.getTaskType().toString().getBytes(StandardCharsets.UTF_8)),
                    encoder.encodeToString(t.title.getBytes(StandardCharsets.UTF_8)),
                    encoder.encodeToString(t.status.toString().getBytes(StandardCharsets.UTF_8)),
                    encoder.encodeToString(t.getDeadline().getBytes(StandardCharsets.UTF_8)),
                    encoder.encodeToString(t.getStartDateTime().getBytes(StandardCharsets.UTF_8)),
                    encoder.encodeToString(t.getEndDateTime().getBytes(StandardCharsets.UTF_8)));
            sb.append(s).append("\n");
        }

        out.write(sb.toString());
    }

    /**
     * Reads and decodes an array of <code>Task</code> data to be used by membot.Membot.
     *
     * @param in The array of data to be loaded.
     */
    public static void load(ArrayList<String> in) {
        Base64.Decoder decoder = Base64.getDecoder();
        for (String s : in) {
            String[] split = s.split(" \\| ");

            byte[] taskType = decoder.decode(split[0]);
            byte[] title = decoder.decode(split[1]);
            byte[] status = decoder.decode(split[2]);
            byte[] deadline = decoder.decode(split[3]);
            byte[] startDateTime = decoder.decode(split[4]);
            byte[] endDateTime = decoder.decode(split[5]);

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
            default:
                assert false : "Unknown Task type";
            }

            restoredTask.setStatus(TaskStatus.valueOf(new String(status, StandardCharsets.UTF_8)));
        }
    }

    /**
     * Finds all <code>Task</code> that has titles that contains the specified keyword.
     *
     * @param keyword The keyword to be used to find <code>Task</code>.
     * @return The list of <code>Task</code> objects that have titles containing the keyword.
     */
    public static ArrayList<Task> find(String keyword) {
        return Task.tasks.stream()
                .filter(x -> x.title.contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Sorts the current list of <code>Task</code> with a specified <code>Comparator</code>.
     *
     * @param c <code>Comparator</code> used to sort the <code>Task</code> list.
     */
    public static void sort(Comparator<? super Task> c) {
        Task.tasks.sort(c);
    }

    /**
     * Retrieves the <code>Task</code> title.
     *
     * @return Title of the task.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Returns the <code>Task</code> type.
     *
     * @return The <code>Task</code> type.
     */
    public abstract TaskType getTaskType();

    /**
     * Returns the deadline attached to the <code>Task</code>.
     *
     * @return The deadline attached to the <code>Task</code>.
     */
    public abstract String getDeadline();

    /**
     * Returns the start dateTime attached to the <code>Task</code>.
     *
     * @return The start dateTime attached to the <code>Task</code>.
     */
    public abstract String getStartDateTime();

    /**
     * Returns the end dateTime attached to the <code>Task</code>.
     *
     * @return The end dateTime attached to the <code>Task</code>.
     */
    public abstract String getEndDateTime();

    /**
     * Returns a <code>String</code> representation of the <code>Task</code>.
     *
     * @return A <code>String</code> representation of the <code>Task</code>.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.printStatus(), this.title);
    }
}

enum TaskStatus {
    NEW,
    COMPLETED
}

