public class ToDo extends Tasks{
    String icon = "[T]";
    public ToDo(String str) {
        super(str);
    }
    public String icon() {
        return icon;
    }
    public String mssg() {
        return super.mssg() + " " + icon + super.symbol() + " " + super.getDesc();
    }
}
