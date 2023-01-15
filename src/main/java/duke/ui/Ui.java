package duke.ui;

import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    
    private Scanner in;
    private PrintStream out;

    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    public void showWelcome() {
        String welcome = "Hello from\n"
                + " ____       _          \n"
                + "|  _ \\ _  _| | ____ _   \n"
                + "| | | | | |  | |/ / _ \\ \n"
                + "| |_| | |_|  |   <  __/  \n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "What can I do for you?\n";
        println(welcome);
    }

    public void showLine() {
        println("-".repeat(40));
    }

    public void showError(String message) {
        println(message);
    }

    public String readCommand() {
        return in.nextLine().trim();
    }

    public void println(Object... lines) {
        for (Object l : lines) {
            out.println(l.toString());
        }
    }
}
