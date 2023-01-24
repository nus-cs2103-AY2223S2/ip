public class MarkTaskAsDoneCommand extends Command {
    private int number;

    public MarkTaskAsDoneCommand(int number) {
        this.number = number;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidArgumentDukeException {
        try {
            taskList.markTaskAsDone(number);
            ui.printMessage("Good job. You have finished this task:\n"
                    + taskList.getTaskString(number)
            );
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentDukeException();
        }
    }
}
