package duke;

public class ToDos extends Task {

    public ToDos(String name) {
        super(name, "T", null, null);
    }

    public ToDos(String name, boolean isDone) {
        super(name, "T", null, null, isDone);
    }

}
