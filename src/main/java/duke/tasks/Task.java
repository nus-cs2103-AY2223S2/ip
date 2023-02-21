package duke.tasks;

/**
 * Encapsulates a task that the user has to complete.
 *
 * @author Tan Matthew Simon Castaneda
 * @version CS2103 AY22/23 Semester 2
 */
public class Task {


    private static String type;
    private String name;
    private boolean isDone;



    /**
     * Constructor to create the task object associated with the user's task.
     *
     * @param name Name of the task that the user has to complete.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    //getter for completion status

    public boolean getCompletionStatus() {
        return this.isDone;
    }


    public String getName() {
        return this.name;
    }

    /**
     * Marks the users Tasks as completed.
     *
     */
    public void markAsDone() {
        this.isDone = true;
        return;
    }

    /**
     * Marks the user's task as undone if it was previously marked as done.
     *
     */
    public void undoTask() {
        this.isDone = false;
        return;
    }

    /**
     * Checks if a tasks name has a word in it
     *
     * @param string The word to be searched.
     * @return True if the task contains the word. False otherwise.
     */
    public boolean hasString(String string) {
        return name.contains(string);
    }

    /**
     * Converts the user's tasks to its string representation
     *
     * @return A string representation of the user's task.
     */
    @Override
    public String toString() {
        if (isDone == true) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }
}
