package tasks;

public class Deadline extends Task{
    protected String deadline;

    public Deadline(String[] rawargs){
//        String[] args = rawargs.split(" ", 2);
        super(rawargs[0]);
        this.deadline = rawargs[1];
    }

    @Override
    public String toString(){
        return "[D]"
                + super.toString()
                + " (by: "
                + this.deadline
                + ")";
    }
}
