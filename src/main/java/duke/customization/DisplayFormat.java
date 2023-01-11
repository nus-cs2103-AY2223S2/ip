package duke.customization;

import java.util.Arrays;

public class DisplayFormat {
    // specify the length of the horizontal bar
    private final int HorizontalLineLength;
    // specify the space of indentation
    private final int indentSpace;

    public DisplayFormat(int HorizontalLineLength, int indentSpace) {
        this.HorizontalLineLength = HorizontalLineLength;
        this.indentSpace = indentSpace;
    }

    public String indent(String input) {
        String space = " ";
        String delimiter = "\n" + space.repeat(this.indentSpace);
        String[] splitedString = input.split("\n");

        return space.repeat(this.indentSpace) + String.join(delimiter, splitedString);
    }

    public void displayWithBar(String message) {
        String underscore = "_";
        String space = " ";
        String bar = space.repeat(indentSpace) + underscore.repeat(this.HorizontalLineLength);
        System.out.println(bar + "\n" + indent(message) + "\n" + bar + "\n");
    }
}
