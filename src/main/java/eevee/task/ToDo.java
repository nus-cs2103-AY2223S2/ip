package eevee.task;

public class ToDo extends Task {

    public ToDo(String name) {
        super(name, "T", null, null);
    }

    public ToDo(String name, boolean isDone) {
        super(name, "T", null, null, isDone);
    }

}
