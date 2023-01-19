package types.data;

public class Todo extends Task {
    private Todo(String s) {
        super(s, "T");
    }

    public static Todo create(String n) {
        return new Todo(n);
    }
}