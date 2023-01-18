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

    public void mark() {
        System.out.println("Nice! I've marked this task as done:\n");
        status = true;
    }

    public void unmark() {
        System.out.println("OK, I've marked this task as not done yet:\n");
        status = false;
    }

    public void talk() {
        System.out.println(this.getStatusIcon() + msg);
    }
}
