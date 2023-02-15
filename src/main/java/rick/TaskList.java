package rick;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import rick.exceptions.RickException;
import rick.exceptions.TaskListFullException;
import rick.exceptions.TaskListInvalidAccessException;
import rick.exceptions.TaskListInvalidIndexException;
import rick.task.RickTask;


/**
 * Represents the main store instance to store user input into the Storage.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class TaskList {
    private static final int recordSize = 100;
    private static int iD = 0;

    //The store instance that writes to storage
    private Storage storage;
    private Ui ui;

    //The unique ID of this store
    private int id;

    /**
     * Constructs and returns the TaskList instance with the provided Storage
     * and Ui instances.
     *
     * @param s The storage instance.
     * @param ui The UI of the app.
     */
    private TaskList(Storage s, Ui ui) {
        this.id = TaskList.iD + 1;
        TaskList.iD += 1;
        this.storage = s;
        this.ui = ui;
    }

    /**
     * Generates and returns TaskList instances with the given Storage and Ui
     * instances.
     *
     * @param s The rick.Storage instance to pass to the Store.
     * @param ui The rick.Ui instance to format output.
     * @return The created TaskList instance with a unique serializable ID.
     */
    public static TaskList create(Storage s, Ui ui) {
        return new TaskList(s, ui);
    }

    /**
     * Adds a RickTask instance to the store, and returns the UI to output to
     * the user.
     *
     * @param input The task to be input.
     * @return The system-generated UI to output to the user.
     * @throws TaskListFullException The exception indicating that the store
     *                               can no longer accept any more tasks.
     */
    public String add(RickTask input) throws TaskListFullException {
        long count;
        if (this.storage.size() >= recordSize) {
            throw new TaskListFullException();
        }
        try {
            count = this.storage.write(input.toDbSchema());
        } catch (NullPointerException e) {
            return ui.error(new RickException("An internal system error occurred"));
        }

        return ui.section(
                "Got it. I've added this task:",
                "  " + input,
                String.format("Now you have %s task%s in the list.", count, count == 1L ? "" : "s")
        );
    }

    /**
     * Marks a task as done, and returns the UI to output to the user.
     *
     * @param i The index of the task within the Store.
     * @return The system-generated UI to output to the user.
     * @throws TaskListInvalidAccessException The exception indicating that an
     *                                        invalid index was provided.
     */
    public String mark(int i) throws TaskListInvalidAccessException {
        if (i < 0 || i >= this.storage.size()) { //Unassigned, invalid index
            throw new TaskListInvalidAccessException();
        }
        RickTask task = this.storage.setDone(i, true);
        assert task != null; //Storage error
        return ui.section(
                "Nice! I've marked this task as done:",
                "  " + task
        );
    }

    /**
     * Marks a task as not done, and returns the UI to output to the user.
     *
     * @param i The index of the task within the Store.
     * @return The system-generated UI to output to the user.
     * @throws TaskListInvalidAccessException The exception indicating that an
     *                                        invalid index was provided.
     */
    public String unMark(int i) throws TaskListInvalidAccessException {
        if (i < 0 || i >= this.storage.size()) { //Unassigned, invalid index
            throw new TaskListInvalidAccessException();
        }
        RickTask task = this.storage.setDone(i, false);
        assert task != null; //Storage error
        return ui.section(
                "OK, I've marked this task as not done yet:",
                "  " + task
        );
    }

    /**
     * Deletes a task, and returns the UI to output to the user.
     *
     * @param i The index of the task in the Store
     * @return The system-generated UI to output to the user.
     * @throws TaskListInvalidAccessException The exception indicating that an
     *                                        invalid index was provided.
     */
    public String delete(int i) throws TaskListInvalidAccessException {
        long size = this.storage.size();
        if (i < 0 || i >= size) {
            throw new TaskListInvalidAccessException();
        }
        RickTask task = this.storage.delete(i);
        assert task != null; //If storage error occurs
        return ui.section(
                "Got it. I've deleted this task:",
                "  " + task,
                String.format(
                        "Now you have %s task%s in the list.",
                        size - 1 == 0L ? "no" : size - 1,
                        size - 1 > 1L ? "" : "s")
        );
    }

    /**
     * Generates and returns a UI segment that displays all tasks that occur
     * on the provided date.
     *
     * @param dt The date provided.
     * @return The list of tasks that occur on that date, if any.
     */
    public String occurOnDate(LocalDate dt) {
        List<RickTask> filtered = this.storage.toList()
                .stream()
                .filter(RickTask -> RickTask.isOnDate(dt))
                .collect(Collectors.toList());
        if (filtered.size() == 0) {
            return "Hooray. No tasks occur on this date.";
        }
        StringBuilder out = new StringBuilder();
        filtered.forEach(RickTask -> out.append("- " + RickTask + "\n"));
        return out.substring(0, out.length() - 1);
    }

    /**
     * Generates a UI response that list all tasks in the list that contain the
     * given search term.
     *
     * @param searchTerm The search term.
     * @return The list of tasks.
     */
    public String searchDescription(String searchTerm) {
        List<RickTask> filtered = this.storage.toList()
                .stream()
                .filter(RickTask -> RickTask.containsTerm(searchTerm))
                .collect(Collectors.toList());
        if (filtered.size() == 0) {
            return "Try again. No tasks have this term.";
        }
        StringBuilder out = new StringBuilder();
        for (int i = 1; i <= filtered.size(); i++) {
            out.append(i + ". " + filtered.get(i - 1) + "\n");
        }
        return out.substring(0, out.length() - 1);
    }

    /**
     * Generates and returns a human friendly interpretation of the tasks in
     * the storage.
     *
     * @return The string interpretation.
     */
    @Override
    public String toString() {
        return this.storage.toString();
    }

    /**
     * Generates and returns a list of all tasks in the Storage that fulfil
     * the given Predicate.
     *
     * @param p The predicate to filter the Storage by
     * @return The list of tasks.
     */
    public Stream<RickTask> filter(Predicate<RickTask> p) {
        return this.storage
                .toList()
                .stream().filter(p);
    }

    /**
     * Marks tasks in the storage as done/not done, and returns the task
     * description to output to the UI.
     *
     * @param index The storage indices to mark as completed.
     * @param isDone Boolean indicating to mark the task as completed or not.
     * @return The output task description.
     * @throws RickException An exception to indicate that this operation was
     *                       unsuccessful.
     */
    public String basicManipulate(int index, boolean isDone) throws RickException {
        if (index < 0 || index > this.storage.size()) {
            throw new TaskListInvalidIndexException(index);
        }
        RickTask task = this.storage.setDone(index - 1, isDone);
        if (task == null) {
            throw new RickException(
                    "Oops. An error occurred when trying to mark the task at index `"
                    + index
                    + "`");
        }
        return task.toString();
    }

    /**
     * Deletes a task in the Storage, and returns its String representation.
     *
     * @param index The storage index of the task to delete.
     * @return The task description to output to the UI.
     * @throws RickException The Exception indicating that this operation was
     *                       unsuccessful.
     */
    public String basicDelete(int index) throws RickException {
        if (index < 0 || index > this.storage.size()) {
            throw new TaskListInvalidIndexException(index);
        }
        RickTask task = this.storage.delete(index - 1);
        if (task == null) {
            throw new RickException(
                    "Oops. An error occurred when trying to delete a task");
        }
        return task.toString();
    }
}
