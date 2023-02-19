package duke; 

/**
 * Class for Task object.
 * Contains task desciptions.
 * 
 * @author Bryan Tan
 */
public class Task {
    private String name;
    private boolean isDone;
    private  String tagging;
    private boolean tagged;

    /**
     * Constructor for Task object.
     * 
     * @param name Description of task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getTask() {
        return this.name;
    }

    /**
     * Marks task as done and set boolean value of current task to true.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks task as undone and set boolean value of current task to false.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Checks if current task is done.
     * 
     * @return true if task is done, false otherwise.
     */
    public boolean isMarked() {
        return this.isDone;
    }

    public void tag(String s) {
        this.tagging = s;
        this.tagged = true;
    }

    public boolean isTagged() {
        return this.tagged;
    }

    public String getTag() {
        return this.tagging;
    }

    @Override
    public String toString() {
        return this.isDone ? "[X] " + this.name : "[ ] " + this.name;
    }
}