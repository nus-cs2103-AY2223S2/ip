package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class Find extends Command{
    protected String keyword;
    public Find(String input) {
        this.keyword = input;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printFindTask();
        
        for(int i = 0; i < tasks.size(); i++) {
            Task currenttask = tasks.getTask(i);
            String taskdescription = currenttask.getDescription();
            if (taskdescription.contains(keyword)) {
                System.out.println((i + 1) + "." + currenttask.toString());
            }
        }
    }
}
