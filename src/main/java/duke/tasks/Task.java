package duke.tasks;

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
