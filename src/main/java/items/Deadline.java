package items;

public class Deadline extends Task{
    private String endDate;
    public Deadline(String description, String endDate) {
        super(description, "D");
        this.endDate = endDate;
    }

    @Override
    public String toString(){
        return "[" + this.getTaskType() + "]" + "[" + this.getStatusIcon() + "]"
                + this.description + "/" + this.endDate;
    }
}
