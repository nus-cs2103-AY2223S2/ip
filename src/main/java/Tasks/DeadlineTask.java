package Tasks;

public class DeadlineTask extends Task {

    String dueDate;
    public DeadlineTask(String taskName, String dueDate){
        super(taskName);
        this.dueDate = dueDate;
    }

    public static Task loadData(String input) {
        String[] inputEvent = input.split("\\|",4);
        Task newEvent = new DeadlineTask(inputEvent[2], inputEvent[3]);
        newEvent.loadCompletionStatus(inputEvent[1]);
        return newEvent;
    }

    @Override
    public String toSaveData() {
        return "D|" + (getCompletionStatus() ? "1" : "0") +
                "|" + taskName + "|" + dueDate;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + dueDate + ")";
    }

}
