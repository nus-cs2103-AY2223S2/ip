public class Task {
    private String name;
    private boolean done;
    private String type;

    public Task(String task) {
        name = task;
        done = false;
        type = "None";
    }

    void mark() {
        done = true;
    }

    void unmark() {
        done = false;
    }

    @Override
    public String toString() {
        String doneStatus;
        if (done) {
            doneStatus = "X";
        } else {
            doneStatus = " ";
        }
        return String.format("[%s] %s", doneStatus, name);
    }
}
