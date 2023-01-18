public class Event extends Task {

    private String time;

    public Event(String s, String time) {
        super(s);
        this.time = time;
    }

    @Override
    public String toString() {
        if (taskDone == false) {
            return "[E][ ] " + this.taskName;
        }
        return "[E][X] " + this.taskName;
    }

}
