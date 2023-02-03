package jarvis.command;

import java.util.List;

import jarvis.duration.Duration;
import jarvis.exception.command.CommandParseException;
import jarvis.exception.command.MissingParameterException;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.task.TimedTask;
import jarvis.ui.Ui;

/**
 * Command class to create timed (fixed-duration) tasks.
 */
public class TimedCommand extends Command {

    public TimedCommand(String body, List<Command> subCommands) {
        super(Action.CREATE_TIMED, body, subCommands);
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        Duration duration = new Duration();
        Command daysCommand = getSubCommand(Action.DURATION_DAYS);
        Command hoursCommand = getSubCommand(Action.DURATION_HOURS);
        Command minutesCommand = getSubCommand(Action.DURATION_MINUTES);
        try {
            if (daysCommand != null) {
                duration.setDays(daysCommand.getBodyAsInt());
            }
            if (hoursCommand != null) {
                duration.setHours(hoursCommand.getBodyAsInt());
            }
            if (minutesCommand != null) {
                duration.setMinutes(minutesCommand.getBodyAsInt());
            }
            System.out.println(duration);
        } catch (CommandParseException e) {
            ui.printError(e.getFriendlyMessage());
            return;
        }

        try {
            ui.print(taskList.addTask(new TimedTask(getBody(), duration)));
        } catch (MissingParameterException e) {
            ui.printError(e.getFriendlyMessage());
        }
    }
}
