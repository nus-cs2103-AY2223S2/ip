package task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public abstract class Task {

    private String description;

    private DateFormat readFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");

    private DateFormat writeFormat = new SimpleDateFormat("E, MMM dd yyyy, h:mm aa");

    private boolean isComplete;

    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    public void mark() {
        this.isComplete = true;
    }

    public void unmark() {
        this.isComplete = false;
    }

    public abstract String save();

    public DateFormat getReadFormat() {
        return this.readFormat;
    }

    public DateFormat getWriteFormat() {
        return this.writeFormat;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[] ");
        sb.append(this.description);
        if (this.isComplete) {
            sb.insert(1, "X");
        }
        return sb.toString();
    }
}
