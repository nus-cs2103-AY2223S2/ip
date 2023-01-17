public class ToDos extends Task{
    public ToDos(String description) {
        super(description);
    }
    public String getTaskIcon() {
        return "T";
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
