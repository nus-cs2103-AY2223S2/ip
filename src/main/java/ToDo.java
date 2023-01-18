public class ToDo extends Task {
    private String type;

    public ToDo(String name) {
        super(name);
        this.type = "T";
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString();
    }
}
