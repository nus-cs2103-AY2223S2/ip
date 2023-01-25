public class ToDo extends Task{

    protected String desc;

    public ToDo(String desc) {
        super(desc);
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String asCSV() {
        return "T," + desc;
    }
}
