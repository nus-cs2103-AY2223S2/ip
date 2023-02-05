package duke.command;


public enum DukeCommand {
    BYE("bye"), MARK("mark"), UNMARK("unmark"), DELETE("delete"), LIST("list"), TODO(
            "todo"), DEADLINE("deadline"), EVENT("event");

    public final String text;

    private DukeCommand(String commandText) {
        this.text = commandText;
    }
}
