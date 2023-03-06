package aot;

import aot.AddTasks.Task;
import aot.munch.Storage;
import java.util.ArrayList;


/**
 * Main class of Munch.
 */
public class Munch {

    static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructor for Munch object
     */
    public Munch() {
        this.tasks = Storage.load(tasks);
    }
}
