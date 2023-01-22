public class Deadline extends Task{
    String tag = "[D]";
    protected String by;

    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    //Override toString
}
