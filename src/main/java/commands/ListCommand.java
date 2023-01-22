package commands;

import model.Task;
import view.Printer;
import view.Printable;

public class ListCommand extends Command {
    public ListCommand(Printable ui) {
        super(ui);
    }

    @Override
    public void execute() {
        String[] tasks = Task.listAll();
        if (tasks.length == 0) {
            this.ui.printlnIndent("Excellent! You do not have any tasks at hand!");
        } else {
            this.ui.printIndent("Here are your updated tasks:");
            Printer.listPrint(Task.listAll());
        }
    }
}
