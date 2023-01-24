import DukeExceptions.DukeException;
import DukeExceptions.DukeStoreFullException;
import DukeExceptions.DukeStoreInvalidAccessException;

import java.time.LocalDate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The main store class to store user input into the Duke system.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class DukeStore {
    private static final int recordSize = 100;
    private static int ID = 0;

    //The store instance that writes to storage
    private DukeFileWriter dfw;

    //The unique ID of this store
    private int id;

    private DukeStore() {
        this.id = DukeStore.ID + 1;
        DukeStore.ID += 1;
        this.dfw = DukeFileWriter.create();
    }

    /**
     * Factory method to create DukeStore instances.
     *
     * @return The created DukeStore instance with a unique serializable ID.
     */
    public static DukeStore create() {
        return new DukeStore();
    }

    /**
     * Given a DukeTask, attempts to add it to the store.
     *
     * @param input The task to be input.
     * @throws DukeStoreFullException The exception indicating that the store
     * can no longer accept any more tasks.
     */
    public void add(DukeTask input) throws DukeStoreFullException {
        long count;
        if (this.dfw.size() >= recordSize) {
            throw new DukeStoreFullException();
        }
        try {
            count = this.dfw.write(input.toDBSchema());
        } catch (NullPointerException e) {
            DukeFormatter.error(new DukeException("An internal system error occurred"));
            return;
        }

        String message = "Got it. I've added this task:\n"
                + "  " + input
                + String.format("\nNow you have %s task%s in the list.",
                            count,
                            count == 1L ? "" : "s"
                    );
        DukeFormatter.section(message);
    }

    /**
     * Given the index of a task in the Store, attempts to mark it as done.
     *
     * @param i The index of the task within the Store.
     * @throws DukeStoreInvalidAccessException The exception indicating that an
     * invalid index was provided.
     */
    public void mark(int i) throws DukeStoreInvalidAccessException {
        if (i < 0 || i >= this.dfw.size()) { //Unassigned, invalid index
            throw new DukeStoreInvalidAccessException();
        }
        DukeTask task = this.dfw.setDone(i, true);
        String message = "Nice! I've marked this task as done:\n" + "  " + task;
        DukeFormatter.section(message);
    }

    /**
     * Given the index of a task in the Store, attempts to mark it as not done.
     *
     * @param i The index of the task within the Store.
     * @throws DukeStoreInvalidAccessException The exception indicating that an
     * invalid index was provided.
     */
    public void unMark(int i) throws DukeStoreInvalidAccessException {
        if (i < 0 || i >= this.dfw.size()) { //Unassigned, invalid index
            throw new DukeStoreInvalidAccessException();
        }
        DukeTask task = this.dfw.setDone(i, false);
        String message = "OK, I've marked this task as not done yet:\n" + "  " + task;
        DukeFormatter.section(message);
    }

    /**
     * Given the index of a task in the Store, attempts to delete it.
     *
     * @param i The index of the task in the Store
     * @throws DukeStoreInvalidAccessException The exception indicating that an
     * invalid index was provided.
     */
    public void delete(int i) throws DukeStoreInvalidAccessException {
        long size = this.dfw.size();
        if (i < 0 || i >= size) {
            throw new DukeStoreInvalidAccessException();
        }
        DukeTask task = this.dfw.delete(i);
        String message = "Noted. I've removed this task:\n" + "  " + task
                + String.format("\nNow you have %s task%s in the list.", size - 1,
                size - 1 == 1L? "": "s");
        DukeFormatter.section(message);
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

    @Override
    public String toString() {
        return this.dfw.toString();
    }
}
