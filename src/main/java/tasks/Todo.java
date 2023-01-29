package tasks;

public class Todo extends Task{
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toStorageFormatString() {
        return "T|" + (isDone ? "1" : "0") + "|" + this.taskDescription;

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
