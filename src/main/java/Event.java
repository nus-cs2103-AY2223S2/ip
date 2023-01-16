public class Event extends Task {
    private String duration;

    public Event(String name, String duration) {
        super(name);
        this.duration = duration;
    }

    @Override
    public String toString() {
        if (super.getStatus()) {
            return String.format("[E][X] %s (%s)\n", super.getTaskName(), this.duration);
        } else {
            return String.format("[E][ ] %s (%s)\n",super.getTaskName(), this.duration);
        }
    }
}
