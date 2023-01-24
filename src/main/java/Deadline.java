public class Deadline extends Task {
    protected String endDate;

    Deadline(String description, String endDate) {
        super(description);
        this.endDate = endDate;
    }

    Deadline(String description, String endDate, boolean isDone) {
        super(description, isDone);
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Deadline / " + getTaskDescription() + "(by" + endDate + ")";
    }

    @Override
    public String serialize() {
        // TODO: Handle case where description contains "|"
        return String.join("|", "D", isDone ? "1" : "0", description, endDate);
    }

    public static Task deserialize(String data) throws DukeException {
        String[] split = splitDataStr(data);
        // TODO: Verify task data (similar to processing queries)
        return new Deadline(split[2], split[3], split[1].equals("1"));
    }
}
