public class Task {
    private final String desc;
    private final boolean done;
    
    private Task(String desc, boolean done) {
        this.desc = desc;
        this.done = done;
    }

    public Task(String desc) {
        this(desc, false);
    }

    @Override
    public String toString() {
        char mark = ' ';
        if (done) {
            mark = 'X';
        }

        return String.format("[%c] %s", mark, this.desc);
    }

    public Task markDone() {
        return new Task(this.desc, true);
    }

    public Task markNotDone() {
        return new Task(this.desc, false);
    }
}
