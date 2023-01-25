package duke.helper;

import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);

    public String readLine() {
        return sc.nextLine();
    }

    /**
     * helper method to print proper indentation.
     *
     * @param toPrint the message to be printed.
     */
    public void print(String toPrint) {
        String line = "____________________________________________________________\n";
        System.out.println(line + " " + toPrint + line);
    }

    public void printError(Exception e) {
        print(e.getMessage());
    }

    public void printGreeting() {
        print("Nyahello! I'm Nyako!\n What can I do for you nya?\n");
    }

    public void printBye() {
        print("Bye bye nya!\n");
        sc.close();
    }
}
