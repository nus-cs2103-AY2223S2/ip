package duke; 

/**
 * Class for Task object.
 * Contains task desciptions.
 * 
 * @author Bryan Tan
 */
public class Task {
    private String name;
    private boolean done;
    private  String tagging;

    /**
     * Constructor for Task object.
     * 
     * @param name Description of task.
     * @return Task object.
     */
    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String getTask() {
        return this.name;
    }

    /**
     * Marks task as done and set boolean value of current task to true.
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Marks task as undone and set boolean value of current task to false.
     */
    public void unmark() {
        this.done = false;
    }

    /**
     * Checks if current task is done.
     * 
     * @return true if task is done, false otherwise.
     */
    public boolean isMarked() {
        return this.done;
    }

    public void tag(String s) {
        this.tagging = "#" + s;
    }

    public String getTag() {
        return this.tagging;
    }

    @Override
    public String toString() {
        return this.done ? "[X] " + this.name : "[ ] " + this.name; 
    }
}