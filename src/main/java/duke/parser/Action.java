package duke.parser;

public enum Action {
    LIST("list"), MARK("mark"), UNMARK("unmark"), DELETE("delete"),
    TODO("todo"), DEADLINE("deadline"), EVENT("event"), ERROR("error");

    private String action;

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