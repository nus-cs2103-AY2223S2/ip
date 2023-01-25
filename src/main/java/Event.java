public class Event extends Task {

    protected String startDate;
    protected String endDate;

    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Override
    public String addToFile() {
        String str = String.format("E | %d | %s | %s-%s ",
                isDone ? 1 : 0, this.description, this.startDate,
                this.endDate);
        return str + "\n";
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s(from: %s to: %s)",
                this.isDone ? "X" : " ", this.description,
                this.startDate, this.endDate);
    }
}

