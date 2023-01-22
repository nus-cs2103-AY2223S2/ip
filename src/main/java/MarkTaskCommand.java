public class MarkTaskCommand extends Command {
    private String taskIndex;

    public MarkTaskCommand(String taskIndex) {
        this.taskIndex = taskIndex;
    }


    @Override
    boolean isExit() {
        return false;
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.markTask(this.taskIndex);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

    }
}
