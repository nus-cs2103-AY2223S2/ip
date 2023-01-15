public class Deadline extends Tasks{
    String icon = "[D]";

    String desc;
    String end;
    public Deadline(String str) {
        super(str);
        String time = super.getDesc().split("/", 2)[1];
        this.desc = super.getDesc().split("/",2)[0];
        this.end =  time.replaceFirst(" ", ": ");
    }
    public String icon() {
        return icon;
    }

    public String mssg() {
        return super.mssg() + " " + this.icon + super.symbol() + " " + this.desc
                +"("+this.end+")";
    }
}
