package tasks;

public class Deadline extends Task{
    protected String deadline;

    public Deadline(String[] args){
        super(args[0]);
        this.deadline = args[1];
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
