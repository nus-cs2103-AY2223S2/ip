public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    protected void mark() {
        isDone = true;
    }

    protected void unmark() {
        isDone = false;
    }

    public String getStatusIndicator() {
        return "[" + (isDone ? "X" : " ") + "]";
    }

    @Override
    public String toString() {
        return "Todo / " + getTaskDescription();
    }

    protected String getTaskDescription() {
        return getStatusIndicator() + description;
    }

    public String serialize() {
        // TODO: Handle case where description contains "|"
        return String.join("|", "T", isDone ? "1" : "0", description);
    }

    public static Task deserialize(String data) throws DukeException {
        String[] split = splitDataStr(data);
        // TODO: Verify task data (similar to processing queries)
        return new Task(split[2], split[1].equals("1"));
    }

    protected static String[] splitDataStr(String dataStr) {
        return dataStr.split("[|]");
    }
}