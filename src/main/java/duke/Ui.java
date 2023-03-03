package duke;

import java.util.Scanner;

/**
 * This is a user interface which reads user input and writes it.
 */
public class Ui {
    private static Scanner sc = new Scanner(System.in);

    /**
     * Returns next line of input from user
     *
     * @return Next line.
     */
    static String nextLine() {
        return sc.nextLine();
    }

    /**
     * Shows output to user.
     *
     * @param lines lines to show
     */
    static void show(String... lines) {
        System.out.println("\t____________________________________________________________");
        for (String line : lines) {
            System.out.println("\t " + line);
        }
        System.out.println("\t____________________________________________________________");
    }
}
