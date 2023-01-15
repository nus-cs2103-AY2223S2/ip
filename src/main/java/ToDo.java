public class ToDo extends Tasks{
    String icon = "[T]";
    String desc;
    public ToDo(String str) {
        super(str);
        this.desc = str.split(" ", 2)[1];
    }
    public String getDesc() {
        return this.desc;
    }
    public String icon() {
        return icon;
    }
    public String mssg() {
        return super.mssg() + " " + icon + super.symbol() + " " + this.desc;
    }
}
