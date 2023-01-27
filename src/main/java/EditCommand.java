import java.io.IOException;

public class EditCommand extends Command {

    private String fullCommand;
    private boolean isMark;
    public EditCommand(String fullCommand, boolean isMark) {
        this.fullCommand = fullCommand;
        this.isMark = isMark;
    }
    public final String COMMAND_WORD = this.isMark ? "mark" : "unmark";

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        try {
            int num = readNumber(fullCommand, tasks.getLength());
            if (isMark) {
                tasks.markTask(num -  1);
            } else {
                tasks.unmarkTask(num - 1);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        ui.showLine();

    }

    public static int readNumber(String command, int len) throws InvalidNumberException {
        int number = Integer.parseInt(String.valueOf(command.charAt(command.length() - 1)));
        if (number > len || number < 1) {
            throw new InvalidNumberException("Oops!! There is no such index number in your list");
        } else {
            return number;
        }
    }

    @Override
    public String toString() {
        return this.COMMAND_WORD;
    }
}
