public class ToDo extends Task {
    public ToDo(String Name) {
        super(Name);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}