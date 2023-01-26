package jarvis.command.subcommand;

/**
 * Subcommand representing a start date.
 */
public class FromCommand extends SubCommand {
    public FromCommand(String body) {
        super(Action.EVENT_FROM, body);
    }
}
