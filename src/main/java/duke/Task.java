package duke;

/**
 * An abstract class depicting something that the user needs to do. It has a name and a status (whether it is
 * done or not).
 */
public abstract class Task {
    private String name;
    private Boolean isDone;

    /**
     * Returns a Task with a given name and sets its status to undone.
     * @param name the name of the Task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Returns a Task with a given name and sets its status accordingly.
     * @param name the name of the Task
     * @param isDone the status of the Task
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Returns the name of the Task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns true if the Task's status is done.
     */
    public Boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the status of the Task in the form of an int, with 0 and 1 representing undone and done
     * respectively.
     */
    public int getMarkedStatus() {
        return this.isDone() ? 1 : 0;
    }

    /**
     * Sets the status of a Task to done.
     */
    public void setMarked() {
        this.isDone = true;
    }

    /**
     * Sets the status of a Task to undone.
     */
    public void setUnmarked() {
        this.isDone = false;
    }

    /**
     * Returns the confirmation message to be printed when a Task gets added to a TaskList.
     * @return the message to be printed
     */
    public String getMessageWhenAdded() {
        return "DukeyList just added a new item: ";
    }

    /**
     * Returns the log string of a task, which is a String containing details of a Task in a certain format.
     * The log string is used to represent a Task when saving Tasks onto the save File.
     * @return log string of a Tasks
     */
    public abstract String getLogString();


    /**
     * Returns the string representation of a Task.
     */
    @Override
    public String toString() {
        if (this.isDone()) {
            return "[X] " + this.getName();
        }
        return "[ ] " + this.name;
    }



}
