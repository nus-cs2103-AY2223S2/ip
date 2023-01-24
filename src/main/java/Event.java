public class Event extends Deadline {
    protected String startDate;

    Event(String description, String startDate, String endDate) {
        super(description, endDate);
        this.startDate = startDate;
    }

    Event(String description, String startDate, String endDate, boolean isDone) {
        super(description, endDate, isDone);
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Event / " + getTaskDescription() + "(from" + startDate + "to" + endDate + ")";
    }

    @Override
    public String serialize() {
        // TODO: Handle case where description contains "|"
        return String.join("|", "E", isDone ? "1" : "0", description, startDate, endDate);
    }

    public static Task deserialize(String data) throws DukeException {
        String[] split = splitDataStr(data);
        // TODO: Verify task data (similar to processing queries)
        return new Event(split[2], split[3], split[4], split[1].equals("1"));
    }
}
