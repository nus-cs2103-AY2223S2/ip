package duke.task;

import duke.utilities.Parser;

/**
 * The type Task.
 */
public abstract class Task {
    public String task_name;
    public String message_add;
    public String message_marked;
    public String message_unmarked;
    public String message_display;
    public String message_delete;
    public boolean done;

    /**
     * Instantiates a new Task.
     *
     * @param name the name
     * @param done the done
     */
    Task(String name, boolean done) {
        this.task_name = name;
        this.done = done;
        this.message_add = "";
        this.message_marked = "";
        this.message_unmarked = "";
        this.message_delete = "";
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
