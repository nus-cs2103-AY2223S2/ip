package duke.task;

import java.util.ArrayList;
import java.util.List;

public class Todo extends Task {


    public Todo(String description) {
        super(description);
    }
    @Override
    public String encode() {
        return "todo"
                + " " + this.description
                + " " + this.isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
