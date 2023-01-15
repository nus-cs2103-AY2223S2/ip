public class ToDo extends Tasks {
    String icon = "[T]";

    public ToDo(String str) {
        super(str);
    }

    public String icon() {
        return this.icon;
    }

    public String mssg() {
        return super.mssg() + " " + this.icon + super.symbol() + " " + super.getDesc();
    }
}
