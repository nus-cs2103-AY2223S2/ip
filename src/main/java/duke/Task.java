package duke;

public class Task {
    private final String details;
    private boolean status;

    public Task(String details) {
        this.details = details;
        this.status = false;
    }

    public String isDone() {
        if (status) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public void mark() {
        status = true;
    }

    public void unmarked() {
        status = false;
    }

    @Override
    public String toString() {
        if (status && (details.contains("[ ]") || details.contains("[X]"))) {
            return details.replace("[ ]", "[X]");
        } else if (!status && (details.contains("[ ]") || details.contains("[X]"))) {
            return details.replace("[X]", "[ ]");
        } else if (!details.contains("[ ]") || !details.contains("[X]")) {
            return isDone() + " " + this.details;
        } else {
            return this.details;
        }
    }
}
