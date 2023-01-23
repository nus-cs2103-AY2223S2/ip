import java.util.Map;

public class AddCommand extends Command {
    private Map<String,String> args;
    AddCommand(Map<String,String> args) {
        this.isExit = false;
        this.args = args;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws Exception {
        String taskType = this.args.get("Command");
        try {
            Task newTask = tl.addTask(taskType, this.args);
            int numTasks = tl.getAllTasks().size();
            ui.reply("New task added:");
            ui.reply(newTask.toString());
            ui.reply("You now have " + numTasks + " task(s) in your list.");
        } catch (Exception e) {
            throw e;
        }
    }
}
