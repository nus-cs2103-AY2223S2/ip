public class Todo extends Task{
    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String toFileString() {
        String mark = this.isDone()? "1": "0";
        return String.format("T | %s | %s", mark, this.getDescription());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
