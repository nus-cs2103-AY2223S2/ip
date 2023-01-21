public class Todo extends Task {


    public Todo(String description,  boolean isDone) {
        super(description, isDone);
    }


    public String statusStringForFile() {
        return String.format("TODO / %s", super.stringFormatForFile());
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
