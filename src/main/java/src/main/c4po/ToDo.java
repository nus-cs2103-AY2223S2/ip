package src.main.c4po;
public class ToDo extends Task {

    public ToDo(String description) {
        super(description, false);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    protected String getTaskFileFormat() {
        return "T" + " | " + super.getTaskFileFormat();
    }
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String getTaskInline() {
        return "[T]" + super.getTaskInline();
    }

    @Override
    public String getTaskInline(Integer index) {
        return index.toString() + ". [T]" + super.getTaskInline();
    }
}
