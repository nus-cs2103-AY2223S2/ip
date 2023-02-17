package duke;

// obtained the idea to use enums this way from 4ndrelim
public enum Request {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    MARK("mark"),
    UNMARK("unmark"),
    LIST("list"),
    DELETE("delete"),
    HELP("help");

    private String name;

    Request (String name) {
        this.name = name;
    }

    public static Request getRequest(String name) throws DukeException {
        for (Request r : values()) {
            if (name.equals(r.name)) {
                return r;
            }
        }
        throw new DukeException(name);
    }


}
