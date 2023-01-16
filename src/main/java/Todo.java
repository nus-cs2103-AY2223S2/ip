public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        if (super.getStatus()) {
            return String.format("[T][X] %s\n", super.getTaskName());
        } else {
            return String.format("[T][ ] %s\n",super.getTaskName());
        }
    }
}
