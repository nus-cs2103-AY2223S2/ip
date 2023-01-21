public class ToDo extends Task{
    protected String type = "[ T ]";
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return type + super.toString();
    }
}
