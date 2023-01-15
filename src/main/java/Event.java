public class Event extends Task {
    String fromDate;
    String toDate;

    Event(String content, String fromToDate) throws InvalidEventException {
        super(content);
        String[] fromToDateSplit = fromToDate.split(" /to ");

        if (fromToDateSplit.length != 2) {
            throw new InvalidEventException("The from or to date of an event task cannot be empty\n");
        }

        this.fromDate = fromToDateSplit[0];
        this.toDate = fromToDateSplit[1];
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %sto: %s)", super.toString(), this.fromDate, this.toDate);
    }
}
