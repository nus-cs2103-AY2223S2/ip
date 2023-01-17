public abstract class DukeTask {
    private boolean isDone = false;
    private final String value;

    public DukeTask(String val) {
        this.value = val;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] %s", (this.isDone ? "X": " "), this.value
        );
    }
}
