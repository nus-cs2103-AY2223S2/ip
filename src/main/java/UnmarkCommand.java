public class UnmarkCommand extends Command{
    private int num;

    public UnmarkCommand(int num) {
        this.num = num;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.unmarkTaskResponse(tasks.markTask(num, false));
        } catch (ArrayIndexOutOfBoundsException e1) {
            ui.taskNotChosenErrorMessage();
        }
    }
}
