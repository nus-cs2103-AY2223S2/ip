package duke;

import java.util.Scanner;

public class Ui {

    protected final String LINE = "____________________________________________________________";
    boolean isTalking;
    Parser parser;
    Scanner scanner;

    /**
     * Constructor for Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Takes in a line of user input using Scanner object and splits it into
     * the first word, command, and the remaining words, content.
     *
     * @return Size 2 string array containing [command, content].
     */
    public String[] receiveInput() {
        String command = scanner.next();
        String content = scanner.nextLine();
        return new String[]{command, content};
    }

    public void printGreeting() {
        System.out.println(LINE);
        System.out.println("hello i am duke");
        System.out.println(LINE);
    }

    public void printResponse(String response) {
        System.out.println(LINE);
        System.out.println(response);
        System.out.println(LINE);
    }

    public void showLoadingError() {
        System.out.println(LINE);
        System.out.println("UH-OH, loading error!");
        System.out.println(LINE);
    }

    public void showSavingError() {
        System.out.println(LINE);
        System.out.println("UH-OH, saving error!");
        System.out.println(LINE);
    }

    public void exitMessage() {
        System.out.println(LINE);
        System.out.println("cya");
        System.out.println(LINE);
    }
}
