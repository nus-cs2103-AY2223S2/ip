public class UnmarkCommand extends Command{

    public int num;

    public UnmarkCommand(int num) {
        this.num = num;
    }
    public void execute(TaskList tasks, Ui ui,Storage storage) {
        ui.printUnmark(tasks, this.num);
    }

    public boolean isExit() {
        return false;
    }
}
