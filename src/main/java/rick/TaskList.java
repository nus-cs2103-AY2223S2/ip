package rick;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import rick.exceptions.RickException;
import rick.exceptions.TaskListFullException;
import rick.exceptions.TaskListInvalidAccessException;
import rick.task.RickTask;


/**
 * The main store class to store user input into the rick.Rick system.
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
     * Restricted constructor to provide the interface between the Storage and
     * the rest of the app.
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
     * Factory method to create TaskList instances.
     *
     * @param s The rick.Storage instance to pass to the Store.
     * @param ui The rick.Ui instance to format output.
     * @return The created TaskList instance with a unique serializable ID.
     */
    public static TaskList create(Storage s, Ui ui) {
        return new TaskList(s, ui);
    }

    /**
     * Given a Rick.task.RickTask, attempts to add it to the store.
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
     * Given the index of a task in the Store, attempts to mark it as done.
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
     * Given the index of a task in the Store, attempts to mark it as not done.
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
     * Given the index of a task in the Store, attempts to delete it.
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
                "Noted. I've removed this task:",
                "  " + task,
                String.format("Now you have %s task%s in the list.", size - 1, size - 1 == 1L ? "" : "s")
        );
    }

    /**
     * Given a date, returns a section that displays all tasks that occur
     * on that date.
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
     * Given a search term, retrieves all tasks in the list that contain it.
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
     * Human friendly interpretation of the tasks in the storage.
     * @return The string interpretation.
     */
    @Override
    public String toString() {
        return this.storage.toString();
    }

    /**
     * Returns a list of all tasks in the Storage that fulfil the given
     * predicate.
     *
     * @param p The predicate to filter the Storage by
     * @return The list of tasks.
     */
    public Stream<RickTask> filter(Predicate<RickTask> p) {
        return this.storage
                .toList()
                .stream().filter(p);
    }
}
