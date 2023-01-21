public class Event extends Task{
    protected String type = "[ E ]";
    protected String fromDate;
    protected String toDate;
    public Event(String description, String fromDate, String toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }


    @Override
    public String toString() {
        return type + super.toString() + "from:" + this.fromDate + " to:" + this.toDate;
    }
}
