public class Deadline extends Task{
    public Deadline(String taskString) {
        super(taskString);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
