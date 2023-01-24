public class MarkTaskAsUndoneCommand extends Command {
    private int number;

    public MarkTaskAsUndoneCommand(int number) {
        this.number = number;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidArgumentDukeException {
        try {
            taskList.markTaskAsNotDone(number);
            ui.printMessage("Ok. I have marked this task as not done:\n"
                    + taskList.getTaskString(number)
            );
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidArgumentDukeException();
        }
    }
}
