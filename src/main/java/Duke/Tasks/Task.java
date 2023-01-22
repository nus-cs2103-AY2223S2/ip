package Duke.Tasks;


/**
 * Represents the Task class
 */
public class Task {

    public boolean exited;
    protected boolean done;
    protected String desc;

    /**
     * The constructor for Task
     * @param done whether the task is done
     * @param desc the description of the task
     */
    public Task(boolean done, String desc) {
        this.done = done;
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
        if (this.done) {
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
        this.done = true;
        return this;
    }

    /**
     * To unmark the task
     * @return Task
     */
    public Task unmark() {
        this.done = false;
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
    public void run(TaskTable table, Monitor monitor, Disk disk) {

    };

    /**
     * The reformat method converts the task to standard output
     * @return
     */
    public String reformat() {
        String d;
        if (done) {
            d = "1";
        } else {
            d = "0";
        }
        return "|" + d + "|" + this.desc;
    }

}
