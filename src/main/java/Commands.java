public enum Commands {
    bye("bye"),
    mark("mark"),
    delete("delete"),
    list("list"),
    todo("todo "),
    deadline("deadline "),
    event("event ");


    String label;
    private Commands(String label) {
        this.label = label;
    }
}
