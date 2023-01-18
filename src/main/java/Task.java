public class Task {
    private String objective;
    private boolean done;

    public Task(String objective) {
        this.objective = objective;
        this.done = false;
    }

    public void mark() {
        done = true;
    }

    public void unmark() {
        done = false;
    }

    @Override
    public String toString() {
        return "[" + (done ? 'X' : ' ') + "] " + objective;
    }
}
