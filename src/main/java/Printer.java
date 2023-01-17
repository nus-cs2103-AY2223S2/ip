import java.util.ArrayList;
import java.util.Arrays;

public class Printer {
    private String str;
    private int maxCharPerLine = 100;
    private int maxLines = 500;
    private String horizontal;

    public Printer() {
        this.horizontal = "_".repeat(this.maxCharPerLine + 4);
    }

    public Printer(int maxCharPerLine, int maxLines) {
        this.maxLines = maxLines;
        this.maxCharPerLine = maxCharPerLine;
        this.horizontal = "_".repeat(maxCharPerLine + 4);
    }

    public void print(String str) {
        String[] lines = str.split("\n");
        ArrayList<String> result = new ArrayList<>();
        for (String line : lines) {
            if (line.length() > maxCharPerLine) {
                int numNewLines = (int) Math.ceil(line.length() / maxCharPerLine);
                String[] newLines = new String[numNewLines];
                for (int i=0; i < numNewLines; i++) {
                    if (i < numNewLines - 1) {
                        newLines[i] = line.substring(100 * i, 100 * i + 1);
                    } else {
                        newLines[i] = line.substring(100 * i);
                    }
                }
                result.addAll(Arrays.asList(newLines));
            } else {
                int padCount = maxCharPerLine - line.length();
                String padding = " ".repeat(padCount);
                result.add(line + padding);
            }
        }
        System.out.println(horizontal);
        result.stream().map(x -> "| " + x + " |").forEach(x -> System.out.println(x));
        System.out.println(horizontal);
    }

    public void printExit() {
        String exitMessage = "Bye! Hope to see you again soon!";
        print(exitMessage);
    }

    public void printWelcome() {
        String welcomeMessage = "Hello! I'm Duke \nWhat can I do for you";
        print(welcomeMessage);
    }
}
