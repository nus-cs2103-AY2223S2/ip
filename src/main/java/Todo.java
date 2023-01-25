public class Todo extends Task{
    private String name;
    private boolean isDone;
    public Todo(String name) {
        super(name, false);
        this.name = name;
        this.isDone = false;
    }
    public Todo(String name, boolean isDone) {
        super(name, isDone);
        this.name = name;
        this.isDone = isDone;
    }
    @Override
    public String getType() {
        return "Todo";
    }
    @Override
    public String getStatus() {
        return isDone ? "1" : "0";
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
