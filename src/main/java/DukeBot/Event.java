package DukeBot;

public class Event extends Task{
    private static final String typeToString = "[E]";
    private final String startDate;
    private final String endDate;

    public Event(String task, String startDate, String endDate) {
        super(task);
        this.type = Types.EVENT;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String status() {
        String status = this.completed ? "[X]" : "[ ]";
        return typeToString + status + details + " (from:" + this.startDate + " to:" + this.endDate + ")";
    }
}
