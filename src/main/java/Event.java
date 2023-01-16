public class Event extends Task {

    private String startTime = "";
    private String endTime = "";

    public Event(int id, String task, String startTime, String endTime) {
        super(id, task);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String printTask() {
        return this.isDone()
                ? "[E][x] " + this.getTask() + "(" + this.startTime + " till " + this.endTime + ")"
                : "[E][ ] " + this.getTask() + "(" + this.startTime + " till " + this.endTime + ")";
    }
}
