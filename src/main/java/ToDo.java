public class ToDo extends Task{
    ToDo(String content) {
        super(content);
    }

    ToDo(String content, boolean done) {
        super(content, done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
