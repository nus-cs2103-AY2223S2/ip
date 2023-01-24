import java.util.Map;

public class AddCommand extends Command {
    private Map<String,String> args;
    private TaskType.Type taskType;

    AddCommand(TaskType.Type type, Map<String,String> args) {
        this.isExit = false;
        this.args = args;
        this.taskType = type;
        this.isSave = true;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws Exception {
        try {
            Task newTask = tl.addTask(this.taskType, this.args);
            int numTasks = tl.getAllTasks().size();
            ui.reply("New task added:");
            ui.reply(newTask.toString());
            ui.reply("You now have " + numTasks + " task(s) in your list.");
        } catch (Exception e) {
            throw e;
        }
    }
}
