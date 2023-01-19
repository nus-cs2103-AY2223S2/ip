public class ToDos extends Task {

    protected String icon = "[T]";

    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
