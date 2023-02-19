public class Event extends Task {
    String tag = "E";
    protected String from;
    protected String to;

    public Event() {
        super.tag = tag;
    }

    //KEEP ALL WORDS SEPARATED BY SPACES
    @Override
    public void genDscp(String input) throws InvalidEvent {
        if (input.isBlank()) {
            throw new InvalidEvent();
        }
        int fromId = input.indexOf("/from");
        int toId = input.indexOf(("/to"));
        if (fromId == -1 || toId == -1) {
            throw new InvalidEvent();
        }
        this.from = input.substring(fromId + 6, toId - 1);
        this.to = input.substring(toId + 4);
        String description = String.format("%s (from: %s to: %s)", input.substring(0, fromId - 1), this.from, this.to);
        super.description = description;
    }

    //Override toString
}
