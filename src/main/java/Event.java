public class Event extends Task {
    String startDate;
    String endDate;

    public Event(String data, String startDate, String endDate) {
        super(data);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder("[E]");
        ret.append(super.toString());
        ret.append("(from: " + startDate);
        ret.append(" to: " + endDate + ")");
        return ret.toString();
    }
}
