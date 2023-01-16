public class Todo extends Task {

    public Todo(String content) {
        super(content);
    }

    public static Todo create(String content) {
        return new Todo(content);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
