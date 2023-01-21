package tasktypes;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        Task.numTask++;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

