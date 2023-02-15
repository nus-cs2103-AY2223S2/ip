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
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        StringBuilder matchingTasks = new StringBuilder("Here are the matching tasks in your list:\n");
        int counter = 0;
        for(int i = 0; i < tasks.size(); i++) {
            Task currenttask = tasks.getTask(i);
            String taskdescription = currenttask.getDescription();
            if (taskdescription.contains(keyword)) {
                counter++;
                matchingTasks.append(counter).append(". ").append(taskdescription).append("\n");
            }
        }
        return matchingTasks.toString();
    }
}
