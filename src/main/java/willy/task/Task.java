package willy.task;

/**
 * Represents a Task
 */
public class Task {
    private String msg;
    private Boolean status;

    /**
     * Creates a task with a msg
     * @param msg
     */
    public Task(String msg) {
        this.msg = msg;
        this.status = false;
    }

    /**
     * Get status icon.
     * @return String
     */
    public String getStatusIcon() {
        return (status ? "[X]" : "[ ]");
    }

    /**
     * get msg in String
     * @return string representation of the msg only
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Marks the status as done and prints a msg
     */
    public String mark() {
        status = true;
        // System.out.println("Nice! I've marked this task as done:\n" + getStatusIcon() + msg);
        String str = "Nice! I've marked this task as done:\n" + getStatusIcon() + msg;
        return str;
    }

    /**
     * Unmarks the status as undone and prints a msg
     */
    public String unmark() {
        status = false;
        // System.out.println("OK, I've unmarked this task:\n" + getStatusIcon() + msg);
        String str = "OK, I've unmarked this task:\n" + getStatusIcon() + msg;
        return str;
    }

    /**
     * Returns the string representation of the task
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + msg;
    }
}
