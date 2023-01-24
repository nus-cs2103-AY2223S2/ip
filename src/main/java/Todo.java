public class Todo extends Task{
    public Todo(String taskText) {
        super(taskText);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    public String writeFile() {
        return String.format("T|%s|%s", this.getCurrentStatus(), this.getTaskText());
    }
}
