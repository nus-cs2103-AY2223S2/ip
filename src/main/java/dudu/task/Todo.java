package dudu.task;

public class Todo extends Task{
    private String name;
    public Todo(String name) {
        super(name, false);
        this.name = name;
    }
    public Todo(String name, boolean isDone) {
        super(name, isDone);
        this.name = name;
    }
    @Override
    public String getType() {
        return "Todo";
    }
    @Override
    public String getStatus() {
        return isDone() ? "1" : "0";
    }
    @Override
    public String getDescription() {
        return name;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
