package duke.tasks;

/**
 * Encapsulates a task that the user has to complete.
 *
 * @author Tan Matthew Simon Castaneda
 * @version CS2103 AY22/23 Semester 2
 */
public class Task {

    String name;
    boolean completionStatus;

    static String type;

    /**
     * Constructor to create the task object associated with the user's task.
     *
     * @param name Name of the task that the user has to complete.
     */
    public Task(String name) {
        this.name = name;
        this.completionStatus = false;
    }

    //getter for completion status

    public boolean getCompletionStatus() {
        return this.completionStatus;
    }

    //getter for the name of the duke.tasks

    public String getName() {
        return this.name;
    }

    /**
     * Marks the users Tasks as completed.
     *
     */
    public void markAsDone() {
        this.completionStatus = true;
        System.out.println("Congrats bro you've done something with your life");
        System.out.println(this.toString());
        return;
    }

    /**
     * Marks the user's task as undone if it was previously marked as done.
     *
     */
    public void undoTask() {
        this.completionStatus = false;
        System.out.println("Stop being useless why u ask me to unmark");
        System.out.println(this.toString());
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
    public String toString(){
        if (completionStatus == true) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }
}
