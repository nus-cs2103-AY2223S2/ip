package duke.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getFileRepresentation() {
        String mark = (super.isDone) ? "X" : " ";

        return "T" + "~" + mark + "~" + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
