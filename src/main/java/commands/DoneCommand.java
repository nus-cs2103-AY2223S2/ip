package commands;

import model.Task;
import utils.InputValidator;
import view.Printable;

public class DoneCommand extends Command {
    public DoneCommand(String input, Printable ui) {
        super(input, ui);
    }

    @Override
    public void execute() {
        if (InputValidator.isCheckInputValid(this.input)) {
            int taskId = Integer.parseInt(this.input.split(" ")[1]);
            try {
                Task.setStatusCompleted(taskId);
                this.ui.printIndent("Well done! The task is marked as completed:");
                this.ui.printIndent(Task.listOne(taskId));

                this.ui.printIndent("");
                new ListCommand(this.ui).execute();
            } catch (IndexOutOfBoundsException e) {
                this.ui.printlnError("Invalid Task ID!");
            }
        } else {
            this.ui.printlnError("Invalid Syntax - \"done [Task ID]\" (e.g. \"done 4\")");
        }
    }
}
