public class Deadlines extends Task {
    public String end;

    public Deadlines(String[] content) {
        super(content[0]);
        this.end = content[1].substring(3);
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + "(by: " + end + ")";
    }


}
