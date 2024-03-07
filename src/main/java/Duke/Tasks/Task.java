package duke.Tasks;

/**
 * Represents the Task class
 */
public class Task {

    public boolean isExited;
    protected boolean isDone;
    protected String desc;

    /**
     * The constructor for Task
     * @param done whether the task is done
     * @param desc the description of the task
     */
    public Task(boolean done, String desc) {
        this.isDone = done;
        this.desc = desc;
    }

    /**
     * To show the description of the task
     * @return String
     */
    public String showDesc() {
        return this.desc;
    }

    /**
     * To show if the task is done
     * @return String
     */
    public String showIfDone() {
        if (this.isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * To mark the task as done
     * @return Task
     */
    public Task mark() {
        this.isDone = true;
        return this;
    }

    /**
     * To unmark the task
     * @return Task
     */
    public Task unmark() {
        this.isDone = false;
        return this;
    }

    /**
     * Override the toString method and return the status of the task
     * @return String
     */
    @Override
    public String toString() {
        return this.showIfDone() + " " + this.desc;
    }

    /**
     * To run the task
     * @param table the task table
     * @param monitor the monitor
     */
    public String run(TaskTable table, Monitor monitor, Disk disk) {
        return "";
    };

    /**
     * The reformat method converts the task to standard output
     * @return String
     */
    public String reformat() {
        String d;
        if (isDone) {
            d = "1";
        } else {
            d = "0";
        }
        return "|" + d + "|" + this.desc;
    }

}
