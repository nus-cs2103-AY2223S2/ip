public class Deadline extends Task{
    protected String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }
    public Deadline(String name, String isDone, String deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }
    @Override
    public String saveFormat() {
        return "T;" + this.name + ";" +this.isDone + ";" + this.deadline;
    }
    @Override
    public String toString() {
        String box;
        if (this.getDone()) {
            box = "[X] ";
        } else {
            box = "[ ] ";
        }
        return"[D]" + box + this.getName() + "(by " + this.getDeadline() +")";
    }
}
