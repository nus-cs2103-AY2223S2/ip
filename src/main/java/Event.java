public class Event extends Task {

    public Event(String desc) {
        // add stuff here to account for time
        super(String.format("%s (from: %s to: %s)",
                desc.substring(0, desc.indexOf("/from") - 1),
                desc.substring(desc.indexOf("/from") + 6, desc.indexOf("/to") - 1),
                desc.substring(desc.indexOf("/to") + 4)), "E");
    }

}