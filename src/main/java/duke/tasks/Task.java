package duke.tasks;

import duke.exceptions.TaskException;

/**
 * Represents task types
 */
public class Task {
    private String name;
    private boolean isMarked;

    /**
     * Initialises input to the class
     *
     * @param name name of the tasks
     */
    public Task(String name) {
        this.name = name;
        this.isMarked = false;
    }

    /**
     * Sets checkMark as true
     */
    public void toBeMarked() {
        this.isMarked = true;
    }

    /**
     * Sets checkMark as false
     */
    public void toBeUnmarked() {
        this.isMarked = false;
    }

    /**
     * Updates name of the item
     */
    public void updateTask(String input) throws TaskException {
        System.out.println("Enter the item name: ");
        this.name = input;
    }

    /**
     * Displays name, date and time of the task
     *
     * @return shows the item
     */
    @Override
    public String toString() {
        return (isMarked ? "[X] " : "[] ") + name;
    }

}
