import java.util.ArrayList;
import java.util.Objects;

/**
 * The main store class to store user input into the Duke system.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class DukeStore {
    private static final int recordSize = 100;
    private static int ID = 0;
    private final ArrayList<DukeTask> records = new ArrayList<>(recordSize);

    //The unique ID of this store
    private int id;

    private DukeStore() {
        this.id = DukeStore.ID + 1;
        DukeStore.ID += 1;
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
        if (this.records.size() > recordSize - 1) {
            throw new DukeStoreFullException();
        }
        try {
            this.records.add(Objects.requireNonNull(input));
        } catch (NullPointerException e) {
            DukeFormatter.error(new DukeException("An internal system error occurred"));
            return;
        }

        String message = "Got it. I've added this task:\n"
                + "  " + this.records.get(this.records.size() - 1).toString()
                + String.format("\nNow you have %s tasks in the list", this.records.size());
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
        if (i < 0 || i >= this.records.size()) { //Unassigned, invalid index
            throw new DukeStoreInvalidAccessException();
        }
        this.records.get(i).setDone();
        String message = "Nice! I've marked this task as done:\n" + "  " + this.records.get(i);
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
        if (i < 0 || i >= this.records.size()) { //Unassigned, invalid index
            throw new DukeStoreInvalidAccessException();
        }
        this.records.get(i).markUndone();
        String message = "OK, I've marked this task as not done yet:\n" + "  " + this.records.get(i);
        DukeFormatter.section(message);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        if (this.records.size() == 0) {
            return "No records are added yet. Add some by typing them!";
        }
        for (int i = 0; i < this.records.size(); i++) {
            out.append(String.format("%s. %s\n", i + 1, this.records.get(i)));
        }
        return out.toString();
    }
}
