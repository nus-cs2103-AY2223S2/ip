public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc);
    }

    @Override
    protected String getType() {
        return "[T]";
    }

    @Override
    public String toString() {
        return String.format("%s%s %s", getType(), getStatusIcon(), this.desc);
    }
}
