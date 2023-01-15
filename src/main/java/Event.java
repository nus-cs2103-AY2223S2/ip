public class Event extends Tasks{
    String icon = "[E]";
    String desc;
    String timeframe;
    public Event(String str) throws unspecTimeException{
        super(str);

        if ((super.getDesc().split("/", 2)).length == 1) {
            throw new unspecTimeException("Please specify a timeframe (from/ ... to/ ...)");
        }

        String time = super.getDesc().split("/", 2)[1];
        String start = time.split("/", 2)[0];
        String end = time.split("/", 2)[1];
        this.desc = super.getDesc().split("/",2)[0];
        this.timeframe = start.replaceFirst(" ", ": ") +
                end.replaceFirst(" ", ": ");
    }
    public String icon() {
        return icon;
    }

    public String getDesc() {
        return this.desc.split(" ", 2)[1]+ "("+this.timeframe+")";
    }
    public String added() {
        return super.added() + " " + icon + super.symbol() + " " + this.desc.split(" ", 2)[1]+
                "("+this.timeframe+")";
    }
    public String deleted(){
        return super.deleted() +  " " + icon + super.symbol() + " " + this.desc.split(" ", 2)[1]+
                "("+this.timeframe+")";
    }
}
