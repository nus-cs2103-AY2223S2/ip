package duke;

/**
 * Task is an abstract class representing something that a user can do
 */
public abstract class Task {
    private String name;
    private Boolean IsDone;


    /**
     * Returns a task with a name and sets the status to undone
     * @param name the name of the task
     */
    public Task(String name) {
        this.name = name;
        this.IsDone = false;
    }

    /**
     * Returns a task with a name and sets the status to done or undone accordingly
     * @param name the name of the task
     * @param isDone the status of the task
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.IsDone = isDone;
    }

    /**
     * Returns the name of a task
     * @return the name of the task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the status of the task, whether done or undone
     * @return the status of the task
     */
    public Boolean isDone() {
        return this.IsDone;
    }

    /**
     * Returns the status of a task in the form of an int, with 0 and 1 representing done
     * and undone respectively.
     * @return the status of the task
     */
    public int isDoneStatus() {
        return this.isDone() ? 1 : 0;
    }

    /**
     * Changes the status of the task to done.
     */
    public void markAsDone() {
        this.IsDone = true;
    }

    /**
     * Changes the status of the task to undone.
     */
    public void unmark() {
        this.IsDone = false;
    }

    /**
     * Returns a message to be printed whenever a task is added.
     * @return the message to be printed
     */
    public String messageWhenAdded() {
        return "DukeyList just added a new item: ";
    }

    /**
     * Returns the log string of a task, which is a string containing details about the task. The log
     * string will be used to save the task locally when the task list is saved.
     * @return the log string
     */
    public abstract String getLogString();


    /**
     * Returns the string representation of a task.
     */
    @Override
    public String toString() {
        if (this.isDone()) {
            return "[X] " + this.getName();
        }
        return "[ ] " + this.name;
    }



}
