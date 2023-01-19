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
        System.out.println("OK, I've marked this task as not done yet:\n" + getStatusIcon() + msg);
        status = false;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + msg;
    }
}
