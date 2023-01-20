public class Deadline extends Task {
    protected String day;

    public Deadline(String description,String day){
        super(description,TypeOfTask.deadline);
        this.day = day;
    }

    @Override
    public String toString() {
        //return "[D]" + super.getDescription() + " (by: " + this.day + ")";
        return String.format("[D][%s] %s (by: %s)",super.getStatusIcon(),super.getDescription(),this.day);
    }
}
