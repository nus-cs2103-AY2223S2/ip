public class Event extends Task {
    String fromDate;
    String toDate;

    Event(String content, String fromToDate) {
        super(content);
        String[] fromToDateSplit = fromToDate.split("/to");
        this.fromDate = fromToDateSplit[0];
        this.toDate = fromToDateSplit[1];
    }

    @Override
    public String toString() {
        return String.format("[E]%s(from:%sto:%s)", super.toString(), this.fromDate, this.toDate);
    }
}
