import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Task {

    private final String NAME;
    static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
    private boolean done;
    private static int count = 0;

    public Task(String name, boolean done) {
        this.NAME = name;
        this.done = done;
        count++;
    }


    public void mark() {
        done = true;
    }
    public void unmark() {
        done = false;
    }

    public int numberTask() {
        return count;
    }

    public void minus() {
        count--;
    }

    public abstract String write(File file);
    
    @Override
    public String toString() {
        return done ? "[X] " + NAME
                    : "[ ] " + NAME;
    }

    public String toWrite() {
        return done ? "1 | " + NAME
                : "0 | " + NAME;
    }
}
