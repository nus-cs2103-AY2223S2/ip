public class MarkCommand extends Command {
    private boolean isToMark;
    private int index;

    public MarkCommand(String commandWord, String commandContent) {
        this.isToMark = commandWord.equals("mark");
        this.index = Parser.parseInt(commandContent, "Item Marking");
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markTask(this.index, this.isToMark);
    }
}
