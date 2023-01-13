package duke.customization;

/**
 * Customize the conversation interface by changing the length of the horizontal bar
 * and the space of indentation.
 */

public class DisplayFormat {
    private final int HorizontalLineLength;
    private final int indentSpace;

    /**
     * Constructor that takes in the length of horizontal line
     * and the space of indentation.
     *
     * @param HorizontalLineLength specify the length of the horizontal bar
     * @param indentSpace specify the space of indentation
     */
    public DisplayFormat(int HorizontalLineLength, int indentSpace) {
        this.HorizontalLineLength = HorizontalLineLength;
        this.indentSpace = indentSpace;
    }

    /**
     * The indent method that places a indentation as specified by the space indent
     * at the start of every line.
     *
     * @param input the text to be indented
     * @return the indented text
     */
    public String indent(String input) {
        String space = " ";
        String delimiter = "\n" + space.repeat(this.indentSpace);
        String[] splitedString = input.split("\n");

        return space.repeat(this.indentSpace) + String.join(delimiter, splitedString);
    }

    /**
     * Display the given message between two horizontal line and add the specified indentation.
     *
     * @param message the message to be display
     */
    public void displayWithBar(String message) {
        String underscore = "_";
        String space = " ";
        String bar = space.repeat(indentSpace) + underscore.repeat(this.HorizontalLineLength);
        System.out.println(bar + "\n" + indent(message) + "\n" + bar + "\n");
    }
}
