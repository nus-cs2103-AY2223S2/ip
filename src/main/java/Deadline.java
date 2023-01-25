public class Deadline extends Tasks {

    private String deadline;

    public Deadline(String content, String deadline) {
        super(content);
        this.deadline = deadline;

    }

    public Deadline(boolean isMarked, String content, String deadline) {
        super(isMarked, content);
        this.deadline = deadline;
    }

    @Override
    public String addDivider() {
        String d = " | ";
        int marked = this.isMarked() ? 1 : 0;
        return "D" + d + marked + d + get_content() + d + deadline;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + "(by: " + this.deadline + ")";
    }
}
