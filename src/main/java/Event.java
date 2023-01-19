public class Event extends Task{
    private String startDetails;
    private String endDetails;

    public Event(String name, String start, String end) {
        super(name);
        this.startDetails = start;
        this.endDetails = end;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + startDetails + " : " + endDetails + ")";
    }


}
