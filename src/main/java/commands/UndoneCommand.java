package commands;

import model.Task;
import utils.InputValidator;
import view.Printable;

public class UndoneCommand extends Command {
    public UndoneCommand(String input, Printable ui) {
        super(input, ui);
    }

    @Override
    public void execute() {
        if (InputValidator.isCheckInputValid(this.input)) {
            int taskId = Integer.parseInt(this.input.split(" ")[1]);
            try {
                Task.setStatusNew(taskId);
                this.ui.printIndent("The task is now marked as not done yet:");
                this.ui.printIndent(Task.listOne(taskId));

                this.ui.printIndent("");
                new ListCommand(this.ui).execute();
            } catch (IndexOutOfBoundsException e) {
                this.ui.printlnError("Invalid Task ID!");
            }
        } else {
            this.ui.printlnError("Invalid Syntax - \"undone [Task ID]\" (e.g. \"undone 3\")");
        }
    }
}
