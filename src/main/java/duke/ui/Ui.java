package duke.ui;

/**
 * Customize the conversation interface by changing the length of the horizontal bar
 * and the space of indentation.
 */

public class Ui {
    private final int horizontalLineLength;
    private final int indentSpace;
    private StringBuilder response = new StringBuilder();

    /**
     * Constructor that sets HorizontalLineLength to be 70 and IndentSpace to be 4 by default.
     */
    public Ui() {
        this.horizontalLineLength = 70;
        this.indentSpace = 4;
    }

    /**
     * Constructor that sets HorizontalLineLength to be the given length
     * and IndentSpace to be the given indent space.
     *
     * @param horizontalLineLength The length of the horizontal line to be set
     * @param indentSpace The length of the indent space to be set
     */
    public Ui(int horizontalLineLength, int indentSpace) {
        this.horizontalLineLength = horizontalLineLength;
        this.indentSpace = indentSpace;
    }

    /**
     * The indent method that places an indentation as specified by the space indent
     * at the start of every line.
     *
     * @param input the text to be indented
     * @return the indented text
     */
    public String indent(String input) {
        String space = " ";
        String delimiter = "\n" + space.repeat(this.indentSpace);
        String[] splitString = input.split("\n");

        return space.repeat(this.indentSpace) + String.join(delimiter, splitString);
    }

    /**
     * Display the given message between two horizontal line and add the specified indentation.
     *
     * @param message the message to be display
     */
    public void displayWithBar(String message) {
        String underscore = "_";
        String space = " ";
        String bar = space.repeat(indentSpace) + underscore.repeat(this.horizontalLineLength);
        System.out.println(bar + "\n" + indent(message) + "\n" + bar + "\n");
    }

    public void reset() {
        this.response = new StringBuilder();
    }

    public String getResponse() {
        return String.valueOf(this.response);
    }

    public void appendResponse(String message) {
        this.response.append(message);
    }
}
