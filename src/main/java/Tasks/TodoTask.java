package Tasks;

public class TodoTask extends Task{

    public TodoTask(String taskName){
        super(taskName);
    }
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
    public static Task loadData(String input) {
        String[] inputEvent = input.split("\\|", 3);


        Task newEvent = new TodoTask(inputEvent[2]);
        newEvent.loadCompletionStatus(inputEvent[1]);
        return newEvent;
    }

    @Override
    public String toSaveData() {
        return "T|" + (getCompletionStatus() ? "1" : "0") +
                "|" + taskName;
    }

}
