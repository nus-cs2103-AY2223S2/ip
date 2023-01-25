public class MarkCommand extends Command{
    public static final String MARK_COMMAND = "mark";
    private final int index;

    public MarkCommand(int index) {
        super(MARK_COMMAND);
        this.index = index;
    }

    @Override
    public void execute(TaskList lst, Ui ui) throws DukeException{
        lst.mark(this.index);
        ui.showMarkedTask(lst.getTask(this.index));
    }
}
