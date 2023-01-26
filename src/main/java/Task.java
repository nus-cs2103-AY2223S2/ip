import java.util.ArrayList;

public abstract class Task {
    protected String objective;
    protected boolean done;

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

    public abstract String[] save();

    @Override
    public String toString() {
        return "[" + (done ? 'X' : ' ') + "] " + objective;
    }
}
