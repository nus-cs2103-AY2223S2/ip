import AddTasks.Task;
import munch.Storage;
import munch.Ui;
import java.util.ArrayList;


/**
 * Main class of Munch.
 */
public class Munch {

    private static Ui ui;
    private Storage storage;
    static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructor for Munch object
     *
     * @param filePath Path of the object file where the task objects are stored in.
     */
    public Munch(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        this.tasks = Storage.load(tasks, filePath);
    }
}
