package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

<<<<<<< Updated upstream
=======
    /**
     * Creates a task object
     *
     * @param description the data to be stored
     */
>>>>>>> Stashed changes
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

<<<<<<< Updated upstream
=======
    /**
     * The status icon X or no icon if the task is done or not done
     *
     * @return a string of "X" or " "
     */
>>>>>>> Stashed changes
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unMark() {

        this.isDone = false;
    }

<<<<<<< Updated upstream
=======
    /**
     * A toString method of the format [x] todo_task
     *
     * @return a string of above format
     */
>>>>>>> Stashed changes
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }


}
