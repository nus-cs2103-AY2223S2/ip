package duke;

import duke.exceptions.DukeException;
import duke.exceptions.TaskListFullException;
import duke.exceptions.TaskListInvalidAccessException;
import duke.task.DukeTask;

import java.time.LocalDate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The main store class to store user input into the Duke system.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class TaskList {
    private static final int recordSize = 100;
    private static int ID = 0;

    //The store instance that writes to storage
    private Storage dfw;
    private Ui ui;

    //The unique ID of this store
    private int id;

    private TaskList(Storage s, Ui ui) {
        this.id = TaskList.ID + 1;
        TaskList.ID += 1;
        this.dfw = s;
        this.ui = ui;
    }

    /**
     * Factory method to create TaskList instances.
     *
     * @param s The duke.Storage instance to pass to the Store.
     * @return The created TaskList instance with a unique serializable ID.
     */
    public static TaskList create(Storage s, Ui ui) {
        return new TaskList(s, ui);
    }

    /**
     * Given a duke.task.DukeTask, attempts to add it to the store.
     *
     * @param input The task to be input.
     * @throws TaskListFullException The exception indicating that the store
     * can no longer accept any more tasks.
     */
    public void add(DukeTask input) throws TaskListFullException {
        long count;
        if (this.dfw.size() >= recordSize) {
            throw new TaskListFullException();
        }
        try {
            count = this.dfw.write(input.toDBSchema());
        } catch (NullPointerException e) {
            ui.error(new DukeException("An internal system error occurred"));
            return;
        }

        String message = "Got it. I've added this task:\n"
                + "  " + input
                + String.format("\nNow you have %s task%s in the list.",
                            count,
                            count == 1L ? "" : "s"
                    );
        ui.section(message);
    }

    /**
     * Given the index of a task in the Store, attempts to mark it as done.
     *
     * @param i The index of the task within the Store.
     * @throws TaskListInvalidAccessException The exception indicating that an
     * invalid index was provided.
     */
    public void mark(int i) throws TaskListInvalidAccessException {
        if (i < 0 || i >= this.dfw.size()) { //Unassigned, invalid index
            throw new TaskListInvalidAccessException();
        }
        DukeTask task = this.dfw.setDone(i, true);
        String message = "Nice! I've marked this task as done:\n" + "  " + task;
        ui.section(message);
    }

    /**
     * Given the index of a task in the Store, attempts to mark it as not done.
     *
     * @param i The index of the task within the Store.
     * @throws TaskListInvalidAccessException The exception indicating that an
     * invalid index was provided.
     */
    public void unMark(int i) throws TaskListInvalidAccessException {
        if (i < 0 || i >= this.dfw.size()) { //Unassigned, invalid index
            throw new TaskListInvalidAccessException();
        }
        DukeTask task = this.dfw.setDone(i, false);
        String message = "OK, I've marked this task as not done yet:\n" + "  " + task;
        ui.section(message);
    }

    /**
     * Given the index of a task in the Store, attempts to delete it.
     *
     * @param i The index of the task in the Store
     * @throws TaskListInvalidAccessException The exception indicating that an
     * invalid index was provided.
     */
    public void delete(int i) throws TaskListInvalidAccessException {
        long size = this.dfw.size();
        if (i < 0 || i >= size) {
            throw new TaskListInvalidAccessException();
        }
        DukeTask task = this.dfw.delete(i);
        String message = "Noted. I've removed this task:\n" + "  " + task
                + String.format("\nNow you have %s task%s in the list.", size - 1,
                size - 1 == 1L? "": "s");
        ui.section(message);
    }

    public String occurOnDate(LocalDate dt) {
        List<DukeTask> filtered = this.dfw.toList()
                .stream()
                .filter(dukeTask -> dukeTask.isOnDate(dt))
                .collect(Collectors.toList());
        if (filtered.size() == 0) {
            return "Hooray. No tasks occur on this date.";
        }
        StringBuilder out = new StringBuilder();
        filtered.forEach(dukeTask -> out.append("- " + dukeTask + "\n"));
        return out.toString();
    }

    /**
     * Given a search term, retrieves all tasks in the list that contain it.
     *
     * @param searchTerm The search term.
     * @return The list of tasks.
     */
    public String searchDescription(String searchTerm) {
        List<DukeTask> filtered = this.dfw.toList()
                .stream()
                .filter(dukeTask -> dukeTask.containsTerm(searchTerm))
                .collect(Collectors.toList());
        if (filtered.size() == 0) {
            return "Try again. No tasks have this term.";
        }
        StringBuilder out = new StringBuilder();
        for (int i = 1; i <= filtered.size(); i++) {
            out.append(i + ". " + filtered.get(i - 1) + "\n");
        }
        return out.toString();
    }

    @Override
    public String toString() {
        return this.dfw.toString();
    }
}
