package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    
    private Scanner in;
    private PrintStream out;

    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showWelcome() {
        String welcome = "Hello from\n"
                + " ____       _\n"
                + "|  _ \\ _  _| | ____ _\n"
                + "| | | | | |  | |/ / _ \\\n"
                + "| |_| | |_|  |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "What can I do for you?";
        println(welcome);
    }

    public void showLine() {
        println("-".repeat(40));
    }

    public void showError(String message) {
        println("Error! [ " + message + " ]");
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
