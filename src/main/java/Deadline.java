public class Deadline extends Task{
    String deadline;

    Deadline(String name) {
        super(name.split("/by ")[0].substring(9));
        this.deadline = name.split("/by ")[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline + ")";
    }
}
