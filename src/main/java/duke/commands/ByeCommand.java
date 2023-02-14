package duke.commands;

import java.util.concurrent.TimeUnit;

import duke.App;
import duke.Duke;
import duke.parser.Arguments;

public class ByeCommand extends Command {
    public ByeCommand() {
        super("bye");
    }

    @Override
    protected void executeInternal(Arguments args, Duke instance) throws ValidationException {
        output("Okay then bye bye!");
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            // Just quit even if the sleep call gets interrupted
        }

        App.sendCloseRequest();
    }
}

