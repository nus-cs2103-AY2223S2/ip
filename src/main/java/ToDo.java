public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toLog() {
        return "T" + super.toLog() + "\n";
    }

    @Override
    public String toString() {
        return("[T]" + super.toString());
    }
}
