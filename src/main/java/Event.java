public class Event extends Task {
    String tag = "[E]";
    protected String from;
    protected String to;

    public Event() {
        super.tag = tag;
    }

    //KEEP ALL WORDS SEPARATED BY SPACES
    @Override
    public void genDscp(String input) throws InvalidEvent{
        String dscp = input.replace("event ", "");
        if (dscp.isBlank()) {
            throw new InvalidEvent();
        }
        int fromId = dscp.indexOf("/from");
        int toId = dscp.indexOf(("/to"));
        if (fromId == -1 || toId == -1) {
            throw new InvalidEvent();
        }
        this.from = dscp.substring(fromId + 6, toId - 1);
        this.to = dscp.substring(toId + 4);
        String task = String.format("%s (from: %s to: %s)", dscp.substring(0, fromId - 1), this.from, this.to);
        super.task = task;
    }

    //Override toString
}
