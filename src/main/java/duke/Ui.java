package duke;

import java.util.Scanner;

public class Ui {
    private static final int INDENT_LEVEL = 4;
    private final Scanner scanner = new Scanner(System.in);

    public void showError(DukeException e) {
        this.show(e.getDukeMessage());
    }

    public void show(String whatToShow) {
        String indentation = " ".repeat(Ui.INDENT_LEVEL);
        String horizontalLine = "_".repeat(60);
        String indentedInput = whatToShow.replaceAll("(?<=^|\n)", indentation);
        
        System.out.println(indentation + horizontalLine);
        System.out.println(indentedInput);
        System.out.println(indentation + horizontalLine + '\n');
    }

    public boolean hasCommand() {
        return this.scanner.hasNextLine();
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void close() {
        this.scanner.close();
    }
}