public class ToDo extends Task {
    public ToDo(String input) {
        super(input);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
