public class Todo extends Task{
    Todo(String name) {
        super(name.substring(5));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
