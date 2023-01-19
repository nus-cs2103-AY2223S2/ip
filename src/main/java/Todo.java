public class Todo extends Task{

    public static Todo create(String text) {
        return new Todo(text);
    }

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
