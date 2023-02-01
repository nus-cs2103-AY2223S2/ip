package duke.Command;

public enum Commands {
    bye("bye"),
    mark("mark"),
    delete("delete"),
    list("list"),
    todo("todo "),
    deadline("deadline "),
    event("event ");


    public final String label;
    Commands(String label) {
        this.label = label;
    }
}
