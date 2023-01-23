public class Event extends Task {
    protected String type;
    protected String startTime;
    protected String endTime;

    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = "E";
    }

    public Event(String description) {
        super();
        int indexOfFrom = description.indexOf("/from");
        int indexOfTo = description.indexOf("/to");
        this.name = description.substring(0, indexOfFrom - " ".length());
        this.startTime = description.substring(indexOfFrom + "/from ".length(), indexOfTo - " ".length());
        this.endTime = description.substring(description.indexOf("/to") + "/to ".length());
        this.type = "E";
    }

    public static String parseStartTime(String s) {
        return s.substring(s.indexOf("/from") + "/from ".length(), s.indexOf("/to") - " ".length());
    }

    public static String parseEndTime(String s) {
        return s.substring(s.indexOf("/to"));
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)", this.type, super.toString(), this.startTime, this.endTime);
    }
}
