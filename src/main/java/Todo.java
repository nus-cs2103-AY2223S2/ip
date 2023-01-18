public class Todo extends Task {

    public Todo(String content) {
        super(content);
    }

    public static Todo create(String content) {
        /**
         * @param content what to place in this task.
         * @returns the output Todo object.
         */
        Parser.handleEmptyField(content, "content", "Todo Creation");
        return new Todo(content);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
