public class ToDo extends Task {
    public ToDo(String str) {
        super(str);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
