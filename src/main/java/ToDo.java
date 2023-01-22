public class ToDo extends Task {
    ToDo(String name) {
        super(name);
    }

    ToDo(String name, String status) {
        super(name, status);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String asTokens() {
        return "T," + super.asTokens();
    }
}
