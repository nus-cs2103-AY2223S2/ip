package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String type;
    protected String detail;
    protected boolean marked;

    public Task(String type, String detail) {
        this.type =type;
        this.detail =detail;
        this.marked =false;
    }

    public Task(String type, String detail, boolean marked) {
        this.type =type;
        this.detail =detail;
        this.marked =marked;
    }

    //abstract public String[] delete() {}

    //abstract public String[] add() {}

    public void mark() {
        this.marked = true;
    }

    public void unmark() {
        this.marked = false;
    }

    @Override
    public String toString(){
        if (marked) {
            return "[" + this.type + "][X] " + this.detail;
        } else {
            return "[" + this.type + "][ ] " + this.detail;
        }
    }
}
