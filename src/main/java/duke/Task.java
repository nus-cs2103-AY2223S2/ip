package duke;
import java.io.Serializable;
import java.time.LocalDate;

class Task implements Serializable{
    boolean done;
    String name;
    public Task(String n) {
        name = n;
    }
    public void mark(boolean isMarked) {
        this.done = isMarked;
    }
    public String toString(){
        return String.format("[%s] %s",this.done ? "X" : " ", this.name);
    }
}
class Todo extends Task {
    public Todo(String n) {
        super(n);
    }
    public String toString(){
        return String.format("[T][%s] %s",this.done ? "X" : " ", this.name);
    }
}
class Deadline extends Task {
    LocalDate by;
    public Deadline(String n, LocalDate b) {
        super(n);
        by = b;
    }
    public String toString(){
        return String.format("[D][%s] %s (by: %s)",this.done ? "X" : " ", this.name, this.by);
    }
}
class Event extends Task {
    LocalDate from;
    LocalDate to;
    public Event(String n, LocalDate f, LocalDate t) {
        super(n);
        from = f;
        to = t;
    }
    public String toString(){
        return String.format("[E][%s] %s (from: %s to %s)",this.done ? "X" : " ", this.name, this.from, this.to);
    }
}
