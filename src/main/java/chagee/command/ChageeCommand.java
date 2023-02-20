package chagee.command;

/**
 * Enum wrapper for commands used by Chagee.
 */
public enum ChageeCommand {
    BYE("bye"), MARK("mark"), UNMARK("unmark"), DELETE("delete"), LIST("list"), TODO(
            "todo"), DEADLINE(
                    "deadline"), EVENT("event"), FIND("find"), VIEW_SCHEDULE("view_schedule");

    public final String text;

    private ChageeCommand(String commandText) {
        this.text = commandText;
    }
}
