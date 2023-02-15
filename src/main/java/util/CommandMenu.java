package util;

/**
 * Enum to manage command menu for help page.
 */
public enum CommandMenu {
    EVENT("Create Event Task", "event party /from 12/2/23 0600 /to 12/2/23 1000"),
    TODO("Create ToDo Task", "todo homework"),
    DEADLINE("Create Deadline Task", "deadline test program /by 25/12/23 1150"),
    MARK("Mark Task as Done", "mark x - x is the index of the task in list"),
    UNMARK("Unmark Task as Not Done", "unmark x - x is the index of the task in list"),
    DELETE("Delete Task from List", "delete x - x is the index of the task in list"),
    FIND("Find Task in List", "find p - will display all tasks with a p in the name"),
    LIST("Display List", "list"),
    BYE("Save Tasks and Exit", "bye");

    private String description;
    private String exampleUsage;

    CommandMenu(String description, String exampleUsage) {
        this.description = description;
        this.exampleUsage = exampleUsage;
    }

    public String getDescription() {
        return description;
    }

    public String getExampleUsage() {
        return exampleUsage;
    }

    /**
     * Displays command menu with a command description
     * and example usage.
     * <p>
     * @return list of commands
     */
    public static String displayCommandMenu() {
        StringBuilder sb = new StringBuilder();
        for (CommandMenu command : CommandMenu.values()) {
            sb.append(command.getDescription().toUpperCase());
            sb.append(System.lineSeparator());
            sb.append("Example usage: " + command.getExampleUsage()
                    + System.lineSeparator());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

}
