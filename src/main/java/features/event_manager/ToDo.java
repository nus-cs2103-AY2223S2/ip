package features.event_manager;

class ToDo extends Task {
    /**
     * Creates a new todo.
     * @param name the name of the new todo.
     * @param isComplete whether if this todo is completed.
     */
    public ToDo(String name, boolean isComplete) {
        super(name, isComplete);
    }

    /**
     * Creates a todo that is completed.
     * @param name the name of the new todo.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Creates a new todo from the tokens.
     * @param tokens the tokens.
     * @return a new todo object.
     */
    public static ToDo fromTokens(String[] tokens) {
        return new ToDo(String.join(" ", tokens));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
