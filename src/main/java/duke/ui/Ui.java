package duke.ui;

import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "-".repeat(50);

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String WELCOME_MESSAGE = "Hi there!\nWhat can I do for you on this fine day :)?";
    private static final String GOODBYE_MESSAGE = "YAY Thank GOD! BYEEEEE";
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    public void showWelcomeMessage() {
        Ui.printDivider();
        System.out.println("Hello from\n" + LOGO);
        System.out.println(WELCOME_MESSAGE);
        Ui.printDivider();
    }

    public void showGoodbyeMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }

    public String readCommand() {
        System.out.println("Enter something here: ");
        String command = this.sc.nextLine();

        while (ignoreEmptyLine(command)) {
            command = this.sc.nextLine();
        }
        return command;
    }

    public boolean ignoreEmptyLine(String line) {
        return line.equals("");
    }


}
