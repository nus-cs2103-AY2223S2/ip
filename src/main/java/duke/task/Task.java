package duke.task;

import duke.Ui;

/**
 * Task is the parent class of ToDo, Event and Deadline. It contains description
 * of task and its completion status.
 */
public abstract class Task {

    public static final String SEPARATOR = " | ";
    private String description;
    private boolean isDone;

    protected Ui ui = new Ui();

    /**
     * Constructor for Task
     * @param description Details of the task
     * @param isDone Keeps track of whether task is completed
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Same as above constructor, except isDone is initalised to false
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public String getDescription() {
        return this.description;
    }
    /**
     * Converts true to 1 and false to 0 when saving in duke.txt
     * @return 1 if true else 0
     */
    public String convertBoolean() {
        return (this.isDone) ? "1" : "0";
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Sets task as completed
     */
    public void setAsDone() {
        isDone = true;
    }

    /**
     * Sets task as uncompleted
     */
    public void setAsUndone() {
        isDone = false;
    }


    /**
     * Finds tasks that contain word in their description
     * @param word String containing common prefix
     * @return true if the description of the task contains word else false
     */
    boolean containsWord(String word) {
        return (description.indexOf(word) == -1) ? false : true;
    }

    public abstract String toSave();

}

