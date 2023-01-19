public class Todo extends Task{

    public static Todo create(String text) throws DukeException{
        if (text.length() < 1) {
            throw new DukeException();
        } else {
            return new Todo(text.substring(1));
        }
    }

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
