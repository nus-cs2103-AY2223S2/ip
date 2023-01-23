package duke;
import java.io.Serializable;
import java.time.LocalDate;

class Task implements Serializable{
    boolean done;
    String name;
    /**
     * Initialize a Task Based on nameString.
     * @param nameString
     */
    public Task(String nameString) {
        name = nameString;
    }

    /**
     * Set this.done to value of isMarked.
     * 
     * @param isMarked
     */
    public void mark(boolean isMarked) {
        this.done = isMarked;
    }

    /**
     * String representation of Task.
     */
    public String toString(){
        return String.format("[%s] %s",this.done ? "X" : " ", this.name);
    }
}
class Todo extends Task {
    /**
     * initialize todo.
     * @param nameString
     */
    public Todo(String nameString) {
        super(nameString);
    }

    /**
     * String representation of todo.
     */
    public String toString(){
        return String.format("[T][%s] %s",this.done ? "X" : " ", this.name);
    }
}
class Deadline extends Task {
    LocalDate by;

    /**
     * Initialize Deadline.
     * @param nameString
     * @param byOption
     */
    public Deadline(String nameString, LocalDate byOption) {
        super(nameString);
        by = byOption;
    }

    /**
     * String representation of Deadline.
     */
    public String toString(){
        return String.format("[D][%s] %s (by: %s)",this.done ? "X" : " ", this.name, this.by);
    }
}
class Event extends Task {
    LocalDate from;
    LocalDate to;

    /**
     * Initialize Event.
     * 
     * @param nameString
     * @param fromOption
     * @param toOption
     */
    public Event(String nameString, LocalDate fromOption, LocalDate toOption) {
        super(nameString);
        from = fromOption;
        to = toOption;
    }

    /**
     * String representation of Event 
     */
    public String toString(){
        return String.format("[E][%s] %s (from: %s to %s)",this.done ? "X" : " ", this.name, this.from, this.to);
    }
}
