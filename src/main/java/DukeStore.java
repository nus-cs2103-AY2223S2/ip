/**
 * The main store class to store user input into the Duke system.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class DukeStore {
    private static final int recordSize = 100;
    private static int ID = 0;
    private String[] records = new String[recordSize];

    //The unique ID of this store
    private int id;

    // The index of the next available slot to add records.
    private int idx = 0;

    private DukeStore() {
        this.id = DukeStore.ID + 1;
        DukeStore.ID += 1;
    }

    public static DukeStore create() {
        return new DukeStore();
    }

    public void add(String input) throws DukeStoreFullException {
        if (this.idx > 99) {
            throw new DukeStoreFullException();
        }
        this.records[this.idx] = input;
        this.idx += 1;
    }

    @Override
    public String toString() {
        String out = "";
        if (this.idx == 0) {
            return "No records are added yet. Add some by typing them!";
        }
        for (int i = 0; i < this.idx; i++) {
            out += String.format("%s. %s\n", i + 1, this.records[i]);
        }
        return out;
    }
}
