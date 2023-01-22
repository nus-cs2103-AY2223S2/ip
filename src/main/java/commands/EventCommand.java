package commands;

import model.Event;
import model.Task;
import utils.*;
import view.Printable;

import java.time.DateTimeException;

public class EventCommand extends Command {
    public EventCommand(String input, Printable ui) {
        super(input, ui);
    }

    @Override
    public void execute() {
        try {
            String[] normalised = InputValidator.normaliseEventInput(input);
            Event task = new Event(normalised[1], normalised[2], normalised[3]);
            this.ui.printIndent(task.toString());

            this.ui.printIndent("");
            new ListCommand(this.ui).execute();
        } catch (IndexOutOfBoundsException | InvalidCommandException | NoStartDateTimeFoundException |
                 NoEndDateTimeFoundException e) {
            this.ui.printlnError("Invalid Syntax - \"event [title] /from [start] /to [end]\"" +
                    "(e.g. \"event piano concert /from tomorrow 3pm /to tomorrow 6pm\")");
        } catch (DateTimeException e) {
            Task.deleteLast();
            this.ui.printlnError(e.getMessage());
        }
    }
}
