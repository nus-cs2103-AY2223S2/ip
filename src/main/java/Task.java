import java.util.Objects;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String symbol;

    public Task(String description)  {
        this.description = description;
        this.isDone = false;
    }

    // constructor for preloaded tasks
    public Task(String description, String isDone) {
        this.description = description;
        this.isDone = Integer.parseInt(isDone) == 1;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getDesc() {
        return this.description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    public abstract String asDataFormat();

    protected String asDataFormat(String... fields) {
        String base = String.join(Storage.SEPARATOR, this.symbol, this.isDone() ? "1" : "0", this.description);
        for (String s : fields) {
            if (!s.isBlank()) {
                base = String.join(Storage.SEPARATOR, base, s);
            }
        }
        return base;
    }

    @Override
    public String toString() {
        return "[" + this.symbol + "][" + this.getStatusIcon() +"] " + this.description;
    }
}