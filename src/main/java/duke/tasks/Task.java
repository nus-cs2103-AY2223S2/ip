package duke.tasks;

/**
 * Encapsulation of the tasks stored in the Chatbot.
 */
public class Task {
    protected String description;
    protected boolean isDone;


    /**
     * Constructs a Task class with the basic information.
     * Basic Information includes Description and Completion Status.
     *
     * @param  description description of the user's task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a String of the Completion Status of the task.
     *
     * @return a String that indicates whether the task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns a boolean to see if the Task is urgent.
     *
     * @return a Boolean that indicates whether the task is Urgent.
     */
    public boolean checkUrgent() {
        return false;
    }


    /**
     * Returns Description of the task.
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
        } else {
            return false;
        }
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

    public boolean completedWithinWeek() {
        return false;
    }

    public boolean addedWithinWeek() {
        return false;
    }

    public boolean containsKeyWord(String keyWord) {
        return this.getDescription().contains(keyWord);
    }

    /**
     * Returns the Completion Status of the task.
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
