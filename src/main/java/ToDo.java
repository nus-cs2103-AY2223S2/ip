public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
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
