public class Event extends Task{
    public Event(String taskString) {
        super(taskString);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
