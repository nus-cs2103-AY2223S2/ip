package Tasks;

public class Events extends Task{
    String dueDate;
    String startDate;
    public Events(String taskName, String startDate, String dueDate){
        super(taskName);
        this.dueDate = dueDate;
        this.startDate = startDate;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(from: " + startDate + " to: " +dueDate+")";
    }
}
