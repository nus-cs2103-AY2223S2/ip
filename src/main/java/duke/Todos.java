package duke;

import duke.Task;

public class Todos extends Task {

    public Todos(String name) {
        super(name);
    }

    public String toString() {
        if (this.getStatus()) {
            return "[T][X] " + this.getName();
        } else {
            return "[T][ ] " + this.getName();
        }
    }

}
