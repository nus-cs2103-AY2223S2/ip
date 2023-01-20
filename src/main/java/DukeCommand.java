public enum DukeCommand {
    BYE("bye"), MARK("mark"), UNMARK("unmark"), LIST("list"), TODO("todo"), DEADLINE(
            "deadline"), EVENT("event");

    public final String text;

    private DukeCommand(String commandText) {
        this.text = commandText;
    }
}
