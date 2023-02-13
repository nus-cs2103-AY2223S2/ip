package jarvis.command.subcommand;

/**
 * Subcommand representing a deadline.
 */
public class ByCommand extends SubCommand {
    public ByCommand(String body) {
        super(Action.DEADLINE_BY, body);
    }
}
