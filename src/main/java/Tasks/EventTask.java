package Tasks;

public class EventTask extends Task{
    String dueDate;
    String startDate;
    public EventTask(String taskName, String startDate, String dueDate){
        super(taskName);
        this.dueDate = dueDate;
        this.startDate = startDate;
    }

    public static Task loadData(String input) {
        String[] inputEvent = input.split("\\|",5);
        Task newEvent = new EventTask(inputEvent[2], inputEvent[3], inputEvent[4]);
        newEvent.loadCompletionStatus(inputEvent[1]);
        return newEvent;
    }

    @Override
    public String toSaveData() {
       return "E|" + (getCompletionStatus() ? "1" : "0") +
               "|" + taskName + "|" + startDate + "|" + dueDate;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(from: " + startDate + " to: " +dueDate+")";
    }
}
