package duke.command;

/**
 * Enum wrapper for commands used by Duke.
 */
public enum DukeCommand {
    BYE("bye"), MARK("mark"), UNMARK("unmark"), DELETE("delete"), LIST("list"), TODO(
            "todo"), DEADLINE(
                    "deadline"), EVENT("event"), FIND("find"), VIEW_SCHEDULE("view_schedule");

    public final String text;

    private DukeCommand(String commandText) {
        this.text = commandText;
    }
}
