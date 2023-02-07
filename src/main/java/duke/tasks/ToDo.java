package duke.tasks;

public class ToDo extends Task{

    public ToDo(String desc, boolean isDone) {
        super(desc, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveFormat() {
        return "T~" + super.saveFormat();
    }
}
