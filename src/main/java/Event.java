public class Event extends Task{
    public Event(String taskText) {
        super(taskText);
    }

    @Override
    public String toString() {
        return String.format("[E]%s", super.toString());
    }
}
