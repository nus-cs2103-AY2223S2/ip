public class Task {
    private final String name;
    private boolean isCompleted;

    public Task (String name) {
        this.name = name;
        this.isCompleted = false;
    }

    public String marking() {
        if (this.isCompleted) {
            return ("[X] ");
        } else {
            return ("[ ] ");
        }
    }

    public void mark() {
        this.isCompleted = true;
    }

    public void unmark() {
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return marking() + this.name;
    }
}
