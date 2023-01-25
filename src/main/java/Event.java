public class Event extends Task {
   String startDayTime;
   String endDayTime;

    public Event(String startTime, String endTime, String description) {
        super(description);
        this.startDayTime = startTime;
        this.endDayTime = endTime;
    }

    @Override
    public String toString() {
        return (isDone? "[E][X] " : "[E][ ] ") + description + ". From: " + this.startDayTime + ". To: " +
                endDayTime;
    }
}
