/** a type of Task that contains information of a start time and an end time
 *
 * @author Wong Yong Xiang
 */
public class Event extends Task {
    protected String from;
    protected  String to;

    /** constructor for the Event class
     *
     * @param description description of the Event
     * @param from start time of Event
     * @param to end time of Event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to= to;
    }

    /** returns the string representation of Event
     *
     * @return string representation of Event class
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String toDataFormatString() {
        int marked = 0;
        if(super.isDone == true) {
            marked = 1;
        }
        return "E / " + marked + " / " + super.description + " - " + this.from + " - " + this.to;
    }
}
