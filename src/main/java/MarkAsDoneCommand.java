public class MarkAsDoneCommand extends Command {
    public String markAtIndex;
    MarkAsDoneCommand(String index) {
        this.isExit = false;
        this.markAtIndex = index;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws Exception {
        boolean alreadyMarked;
        try {
            int i = Integer.valueOf(this.markAtIndex) - 1;
            alreadyMarked = tl.markAsDone(i);
            if (alreadyMarked) {
                ui.reply(tl.getTask(i).getDesc() + " already marked as done!");
            } else {
                ui.reply("Marked " + tl.getTask(i).getDesc() + " as done!");
            }
        } catch (NumberFormatException e) {
            throw new Exception("Please specify the task by its index number.");
        } catch (IndexOutOfBoundsException e) {
            throw new Exception("Seems like this task doesn't exist.");
        }
    }
}
