public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[T]" + super.toString());
        return sb.toString();
    }
}
