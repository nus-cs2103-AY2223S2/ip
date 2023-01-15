public class Deadline extends Tasks{
    String icon = "[D]";

    String desc;
    String end;
    public Deadline(String str) throws unspecTimeException {
        super(str);
        if ((super.getDesc().split("/", 2)).length == 1) {
            throw new unspecTimeException("Please specify a deadline (by/ ... )");
        }
        String time = super.getDesc().split("/", 2)[1];
        this.desc = super.getDesc().split("/",2)[0];
        this.end =  time.replaceFirst(" ", ": ");
    }
    public String icon() {
        return icon;
    }

    public String getDesc(){
        return this.desc.split(" ", 2)[1] + "("+this.end+")";
    }

    public String added() {
        return super.added() + " " + this.icon + super.symbol() + " " + this.desc.split(" ", 2)[1]
                +"("+this.end+")";
    }
    public String deleted() {
        return super.deleted() + " " + icon + super.symbol() + " " + this.desc.split(" ", 2)[1] +
                "(" + this.end + ")";
    }
}
