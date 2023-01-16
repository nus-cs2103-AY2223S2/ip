public class ToDo extends Task {
    protected String type;

    public ToDo(String description) {
        super(description);
        this.type = "T";
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", this.type, super.toString());
    }
}
