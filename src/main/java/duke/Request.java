package duke;



// obtained the idea to use enums this way from 4ndrelim

/**
 * An enum class that stores the different types of requests as fixed constants
 */
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

    /**
     * The constructor for the Request enum
     * @param name The name of the constant
     */
    Request (String name) {
        this.name = name;
    }

    /**
     * Method to obtain the title of the request entered by the user
     * @param name The request type entered in by the user
     * @return The constant representing the request entered by the user
     * @throws DukeException
     */
    public static Request getRequest(String name) throws DukeException {
        for (Request r : values()) {
            if (name.equals(r.name)) {
                return r;
            }
        }
        throw new DukeException(name);
    }


}
