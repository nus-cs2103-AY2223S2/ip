package vic.gui.model;


/**
 * A Model Class use to model the attribute for DialogBox Input
 */
public class DialogBoxInput {
    private final int lineNumber;
    private final String input;

    /**
     * Constructor for DialogBoxInput
     *
     * @param input the input to be print
     * @param lineNumber number of lines for the input
     */
    public DialogBoxInput(String input, int lineNumber) {
        this.input = input;
        this.lineNumber = lineNumber;
    }


    public String getInput() {
        return input;
    }

    public int getLineNumber() {
        return lineNumber;
    }


}
