public class Todo extends Task{

    public String todo;

    public Todo(String description) {
        super(description);
        this.todo = description.split(" ",2)[1];
    }

    public String toString() {
        return "[T]" + "[" + super.getStatusIcon()+ "] " + this.todo;
    }
}
