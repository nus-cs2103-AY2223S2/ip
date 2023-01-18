package duke.task;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public class Task implements Serializable {
    public String desc;
    public boolean isDone;
    static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("eee, d MMM uuuu");

    public Task(String desc){
        this.desc = desc;
        this.isDone = false;
    }

    public void mark(){
        this.isDone = !this.isDone;
        // return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + desc;
    }

    public String toString(int index) {
        return index + ". " + this;
    }
}
