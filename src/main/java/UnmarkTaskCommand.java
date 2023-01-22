public class UnmarkTaskCommand extends Command {

    private String taskIndex;

    public UnmarkTaskCommand(String taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    boolean isExit() {
        return false;
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.unmarkTask(taskIndex);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
