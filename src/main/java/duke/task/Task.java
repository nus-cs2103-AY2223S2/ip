package duke.task;

/**
 * The type Task.
 */
public abstract class Task {
    public String taskName;
    public String messageAdd;
    public String messageMarked;
    public String messageUnmarked;
    public String messageDisplay;
    public String messageDelete;
    public boolean done;

    /**
     * Instantiates a new Task.
     *
     * @param name the name
     * @param done the done
     */
    Task(String name, boolean done) {
        this.taskName = name;
        this.done = done;
        this.messageAdd = "";
        this.messageMarked = "";
        this.messageUnmarked = "";
        this.messageDelete = "";
    }

    /**
     * Add.
     */
    public abstract void add();

    /**
     * Marked.
     */
    public abstract void marked();

    /**
     * Unmarked.
     */
    public abstract void unmarked();

    /**
     * Display.
     */
    public abstract void display();

    /**
     * Delete.
     */
    public abstract void delete();
}
