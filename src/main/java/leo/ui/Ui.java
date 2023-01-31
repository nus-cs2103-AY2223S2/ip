package leo.ui;

import leo.task.LeoTaskException;

public class Ui {
    public void greetUser() {
        printDivider();
        System.out.println("Hello! I'm Leo");
        System.out.println("What can I do for you?");
        printDivider();
    }

    public static void printDivider() {
        for (int i = 0; i < 25; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    public static void printResponse(String response) {
        printDivider();
        System.out.println(response);
        printDivider();
    }


    public static void printError(LeoTaskException e) {
        printDivider();
        System.out.println(e.getMessage());
        printDivider();
    }

    public void printExit() {
        printDivider();
        System.out.println("It was nice talking, see you soon!");
        printDivider();
    }
}
