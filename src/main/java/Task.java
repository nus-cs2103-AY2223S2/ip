public abstract class Task {
    protected final String desc;
    protected final boolean done;
    
    protected Task(String desc, boolean done) {
        this.desc = desc;
        this.done = done;
    }

    @Override
    public String toString() {
        char mark = ' ';
        if (done) {
            mark = 'X';
        }

        return String.format("[%c] %s", mark, this.desc);
    }

    public abstract Task markDone();

    public abstract Task markNotDone();
}
