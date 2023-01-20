public class ToDo extends Task {
    public ToDo(String str) {
        super(str);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
