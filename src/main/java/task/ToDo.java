package task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String save() {
        return this.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[T]");
        sb.append(super.toString());
        return sb.toString();
    }
}
