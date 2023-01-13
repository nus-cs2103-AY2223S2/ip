public class Event extends Task{
    private Duration duration;
    public Event(String taskDescription, Duration duration) {
        super(taskDescription);
        this.duration = duration;
    }
    @Override
    public String toString() {

        return "[E]" + super.toString() + this.duration.toString();
    }
}
