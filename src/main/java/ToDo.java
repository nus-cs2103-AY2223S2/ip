public class ToDo extends Task{

    protected String desc;

    public ToDo(String desc) {
        super(desc);
        this.desc = desc;
    }

    public ToDo(String desc, boolean b) {
        super(desc, b);
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String asCSV() {
        if (super.isDone) {
            return "T,1," + desc;
        } else {
            return "T,0," + desc;
        }
    }
}
