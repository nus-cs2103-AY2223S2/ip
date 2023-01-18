public class Todo extends Task {

    protected Todo(String name) {
        super.name = name;
    }

    @Override
    public String toString() {
        return "[T] " + this.TasktoString();
    }
}
