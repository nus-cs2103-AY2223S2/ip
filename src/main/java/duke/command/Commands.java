package duke.command;

public enum Commands {
    // TODO: use enum instead of text when parsing commands
    bye("bye"),
    mark("mark"),
    delete("delete"),
    list("list"),
    todo("todo "),
    deadline("deadline "),
    event("event "),
    find("find ");


    public final String label;
    Commands(String label) {
        this.label = label;
    }
}
