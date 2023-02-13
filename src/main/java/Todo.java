public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
//        return "[T]" + super.toString().replaceFirst("[]", "");
//        should use regular expression to avoid magic number, but haven't figured out how to do
        return "[T]" + super.toString().substring(3);
    }
}