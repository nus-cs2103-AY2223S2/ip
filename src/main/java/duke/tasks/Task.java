package duke.tasks;

public class Task {
    protected String description;
    protected boolean isDone;


    /**
     * The building block for the 3 other tasks: Deadline, ToDos and Event.
     *
     * @param  description description of the user's task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Completion Status of the task.
     *
     * @return a String that indicates whether the task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }


    /**
     * Description of the task.
     *
     * @return a String that details the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Check if the task is null
     *
     * @return boolean to check if its a null task
     */
    public boolean emptyTask() {
        if (description.equals("")) {
            return true;
        } else return false;
    }

    /**
     * Mark the task as Done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmark the task, indicating it is not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    public boolean containsKeyWord(String keyWord) {
        return this.getDescription().contains(keyWord);
    }

    /**
     * Status of its completion status
     *
     * @return Status of its completion status but in binary format for saving in Storage
     */
    public String saveString() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * All the Information of the task
     *
     * @return a String of all the information of the task to be printed by the Ui
     */
    @Override
    public String toString() {
        return String.format("%s %s", this.getStatusIcon(), this.getDescription());
    }
}
