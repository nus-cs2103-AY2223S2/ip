package features.todos_manager;

/**
 * This shall resemble a TodoItem. The reason that no modifiers was added was
 * because this class shall be package private.
 */
class TodoItem {
    /**
     * Creates a new ListItem instance.
     * @param name the name of the list item.
     */
    TodoItem(String name) {
        this.name = name;
    }

    /**
     * The name of the list item.
     */
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
