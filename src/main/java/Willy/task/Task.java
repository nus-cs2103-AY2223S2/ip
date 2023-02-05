package Willy.task;

public class Task {
    private String msg;
    private Boolean status;

    public Task(String msg) {
        this.msg = msg;
        this.status = false;
    }

    public String getStatusIcon() {
        return (status ? "[X]" : "[ ]");
    }

    public String getMsg() {
        return msg;
    }

    public void mark() {
        status = true;
        System.out.println("Nice! I've marked this task as done:\n" + getStatusIcon() + msg);
    }

    public void unmark() {
        status = false;
        System.out.println("OK, I've unmarked this task:\n" + getStatusIcon() + msg);
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + msg;
    }
}
