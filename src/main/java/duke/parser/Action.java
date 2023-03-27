package duke.parser;

/**
 * Enumerates actions for different command types
 */
enum Action {
    DEADLINE("deadline"), DELETE("delete"), ERROR("error"),
    EVENT("event"), LIST("list"), MARK("mark"),
    TODO("todo"), UNMARK("unmark"), FIND("find"),
    BYE("bye"), SORT("sort");

    private final String action;

    Action(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public static Action getAction(String action) {
        for (Action a : Action.values()) {
            if (a.getAction().equalsIgnoreCase(action)) {
                return a;
            }
        }
        return ERROR;
    }
}
