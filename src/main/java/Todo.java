public class Todo extends Task {


    public Todo(String description) {
        super(description);
    }

    /**
     * string representation of the todo
     * @return todo string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
