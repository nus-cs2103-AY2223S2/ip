package features.todos_manager;

/**
 * This shall resemble a ListItem. The reason that no modifiers was added was
 * because this class shall be package private.
 */
class ListItem {
    /**
     * Creates a new ListItem instance.
     * @param name the name of the list item.
     */
    ListItem(String name) {
        this.name = name;
    }

    /**
     * The name of the list item.
     */
    private String name;
}
