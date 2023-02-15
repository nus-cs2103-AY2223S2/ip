package duke.tasks;

/**
 * Abstract class to be inherited by specific types of tasks. Some common task manipulation methods implemented.
 *
 * @author jengoc415
 */
public abstract class Task {
    protected String instruction;
    protected boolean isDone;

    protected String modifiedInstr;
    protected String description;

    public Task(String instr) {
        this.instruction = instr;
        this.isDone = false;
    }

    public String getInstruction() {
        return this.instruction;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public boolean isCompleted() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "]";
    }
}
