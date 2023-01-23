public class ToDo extends Tasks {
    String icon = "[T]";
    String desc;
    public ToDo(String str) throws Exception {
        super(str);
        if(!str.contains("todo")) {
            throw new unrecogException("");
        }
        if (str.equals("todo")) {
            throw new emptyDescException("");
        }
        this.desc = str.split(" ", 2)[1];
    }
    public String getDesc() {
        return this.desc;
    }
    public String icon() {
        return icon;
    }
    public String added() {
        return super.added() + " " + icon + super.symbol() + " " + this.desc;
    }
    public String deleted() {
        return super.deleted() + " " + icon + super.symbol() + " " + this.desc;
    }
}
