public class Deadline extends Task {

    private String time = "";

    public Deadline(int id, String task, String time) {
        super(id, task);
        this.time = time;
    }

    @Override
    public String printTask() {
        return this.isDone()
                ? "[D][x] " + this.getTask() + "(" + this.time + ")"
                : "[D][ ] " + this.getTask() + "(" + this.time + ")";
    }
}
