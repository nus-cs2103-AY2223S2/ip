public class MarkAsUndoneCommand extends Command {
    private String unmarkAtIndex;

    MarkAsUndoneCommand(String index) {
        this.isExit = false;
        this.unmarkAtIndex = index;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        boolean alreadyMarked;
        try {
            int i = Integer.valueOf(this.unmarkAtIndex) - 1;
            alreadyMarked = tl.unmarkDone(i);
            if (alreadyMarked) {
                ui.reply(tl.getTask(i).getDesc() + " is already undone!");
            } else {
                ui.reply("Unmarked " + tl.getTask(i).getDesc() + ".");
            }
        } catch (NumberFormatException e) {
            ui.reply("Please specify the task by its index number.");
        } catch (IndexOutOfBoundsException e) {
            ui.reply("Seems like this task doesn't exist.");
        }
    }
}
