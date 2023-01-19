package commands;
import tasks.Task;

public class Mark implements Command {
    private Task task;

    public Mark(Task task){
        this.task = task;
    }

    public String execute(){
        return this.task.mark();
    }
}
