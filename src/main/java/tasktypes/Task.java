package tasktypes;

public class Task {

    public static int numTask = 0;
    public String description;
    public boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    public void markUndone() {
        this.done = false;
    }

    @Override
    public String toString() {
        String status;
        if (this.done) {
            status = "[X]";
        } else {
            status = "[ ]";
        }
        return status + " " + this.description;
    }
}

