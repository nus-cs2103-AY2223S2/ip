package duke.task;
public class ToDo extends Task {
    public ToDo(String input) {
        super(input);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toTxtString() {
        return "T" + super.toTxtString();
    }
}
