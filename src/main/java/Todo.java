public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    @Override
    public String printTask() {
        return String.format("[T]%s", super.printTask());
    }
}
