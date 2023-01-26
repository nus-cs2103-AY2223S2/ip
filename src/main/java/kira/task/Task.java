package kira.task;

/**
 * Task represents all possible task.
 */
public abstract class Task {

    private String description;
    private boolean isDone;

    /**
     * Constructs an abstract task with a description.
     * 
     * @param description
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as completed
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as incompleted
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Formats the task into saveload readable format.
     * 
     * @return formatted string
     */
    public String saveFormat() {
        String[] temp = new String[] {this.description, this.isDone ? "y" : "n"};
        return String.join("\",\"", temp);
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        String done = this.isDone ? "x" : " ";
        ret.append("[" + done + "] ");
        ret.append(this.description);
        return ret.toString();
    }
}
