package task;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Task implements Serializable {

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

    public abstract boolean hasDate(LocalDate date);

    public abstract Task markDone();

    public abstract Task markNotDone();
}
