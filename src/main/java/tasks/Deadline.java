package tasks;

public class Deadline extends Task{
    protected String deadline;
    private static final String PREFIX = "D";

    public Deadline(String desc, String deadline){
//        String[] args = rawargs.split(" ", 2);
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public String getPrefix() {
        return PREFIX;
    }

    @Override
    public String save() {
        StringBuilder response = new StringBuilder("");
        response.append(getPrefix() + ",");
        response.append(isDone + ",");
        response.append(description + ",");
        response.append(deadline + "\n");
        return response.toString();
    }

    @Override
    public String toString(){
        return super.toString()
                + " (by: "
                + this.deadline
                + ")";
    }
}
