public class Todo extends Task {

    public Todo(String content) {
        super(content);
    }

    public static Todo create(String content) {
        /**
         * @param content what to place in this task.
         * @returns the output Todo object.
         */
        if (content.equals("")) {
            throw new InputFormatException("Todo Creation", "Haiya content empty.", null);
        }
        return new Todo(content);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
