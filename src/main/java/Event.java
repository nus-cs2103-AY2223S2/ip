public class Event extends Task{

    //default constructor
    protected String startTime;
    protected String endTime;

    public Event(String description,String startTime,String endTime){
        super(description,TypeOfTask.event);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString(){
        return String.format("[E][ ] %s (from: %s to %s",super.getDescription(),this.startTime,this.endTime);
    }
}
