package duke.ui;

import java.util.Scanner;

public class TextUi {
    private final Scanner in;
    private static int numberOfInput = 0;

    public TextUi() {
        in = new Scanner(System.in);
    }

    public String getUserCommand() {
        if (numberOfInput == 0) {
            System.out.println("What can I do for you?");
        } else {
            System.out.println("What else can I do for you?");
        }
        numberOfInput++;
        String input = in.nextLine();
        return input;
    }
}
