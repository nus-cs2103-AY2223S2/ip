package jarvis.command.subcommand;

/**
 * Subcommand representing an end date.
 */
public class ToCommand extends SubCommand {
    public ToCommand(String body) {
        super(Action.EVENT_TO, body);
    }
}
