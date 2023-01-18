public class Todo extends Task{
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        String s;
        if (this.completed) {
            s = "[T][X] " + this.taskName;
        } else {
            s = "[T][ ] " + this.taskName;
        }
        return s;
    }
}
