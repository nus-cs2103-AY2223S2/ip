public class Event extends Task{

    protected String startTime;
    protected String endTime;

    public static Event create(String str) throws DukeException{
        if (str.length() < 1) {
            throw new DukeException();
        } else {
            String[] text = str.substring(1).split(" /");
            if (text.length < 3) {
                throw new DukeException();
            } else {
                String desc = text[0];
                String from = text[1].substring(5);
                String to = text[2].substring(3);
                return new Event(desc, from, to);
            }
        }
    }

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }



    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
