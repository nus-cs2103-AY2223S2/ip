package duke.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }
    public void printMsg(String[] msgs) {
        System.out.println("____________________________________________________________");
        for (String msg : msgs) {
            System.out.println(msg);
        }
        System.out.println("____________________________________________________________");
    }

    public void printMsg(String msg) {
        System.out.println("____________________________________________________________");
        System.out.println(msg);
        System.out.println("____________________________________________________________");
    }

    public void printList(String[] list) {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < list.length; i++) {
            System.out.println(String.format("%d. %s", i + 1, list[i]));
        }
        System.out.println("____________________________________________________________");
    }
    /**
     * Prints a formatted list of strings to the console.
     *
     * @param msg The message to be printed before the list.
     * @param list The array of strings to be printed as a list.
     */
    public void printList(String msg, String[] list) {
        System.out.println("____________________________________________________________");
        System.out.println(msg);
        for (int i = 0; i < list.length; i++) {
            System.out.println(String.format("%d. %s", i + 1, list[i]));
        }
        System.out.println("____________________________________________________________");
    }

    public void printWelcomeMsg() {
        String[] welcomeMsg = {"Hello I am Duke", "What can I do for you?"};
        printMsg(welcomeMsg);
    }

    public String readInput() {
        return this.sc.nextLine();
    }

}
