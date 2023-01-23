package commands;

import model.Event;
import model.Task;
import utils.*;
import view.Printable;

import java.time.DateTimeException;

/**
 * Represents a command which creates a <code>Task</code> that has a start and end dateTime attached to it.
 */
public class EventCommand extends Command {
    /**
     * Generates a <code>EventCommand</code> object.
     *
     * @param input User input into the application.
     * @param ui A Printable object used for UI display.
     */
    public EventCommand(String input, Printable ui) {
        super(input, ui);
    }

    /**
     * Creates a <code>Event</code> task.
     */
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
