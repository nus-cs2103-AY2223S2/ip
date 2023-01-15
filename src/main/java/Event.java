public class Event extends Tasks {
    String icon = "[E]";
    String desc;
    String timeframe;

    public Event(String var1) {
        super(var1);
        String time = super.getDesc().split("/", 2)[1];
        String start = time.split("/", 2)[0];
        String end = time.split("/", 2)[1];
        this.desc = super.getDesc().split("/", 2)[0];
        this.timeframe = start.replaceFirst(" ", ": ") + end.replaceFirst(" ", ": ");
    }

    public String icon() {
        return this.icon;
    }

    public String mssg() {
        return super.mssg() + " " + this.icon + super.symbol() + " " + this.desc + "(" + this.timeframe + ")";
    }
}

