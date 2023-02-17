package duke.parsing;

/**
 * Methods related to formatting a message.
 */
public class MessageFormat {
    /**
     * Crafts string output for showing number of tasks in a list.
     * @param numTask Number of tasks in the list
     * @return Crafted message
     */
    public static String numTaskToString(int numTask) {
        return String.format("Now you have %d task%s in the list", numTask, numTask == 1 ? "" : "s");
    }

    /**
     * Indents a message by a specified indentation level.
     *
     * @param msg Message to indent
     * @param indendationLevel Number of indents
     * @return Indented message.
     */
    public static String indentString(String msg, int indendationLevel) {
        String indent = "  ".repeat(indendationLevel);
        String[] lines = msg.split("\n");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            result.append(indent + lines[i] + (i + 1 < lines.length ? "\n" : ""));
        }
        return result.toString();
    }
}
