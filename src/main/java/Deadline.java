public class Deadline extends Task {

    protected String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        String[] strArr = deadline.split(" ", 2);
        return name + " (" + strArr[0] + ":" + " " + strArr[1] + ")";
    }
}
