package duke.duke;

import java.util.Scanner;
public class Ui {
    private static String logo;
    private static String welcome;
    private final Scanner scanner;
    private static String divider;

    Ui() {
        this.logo = "GPTGPTGPT";
        this.scanner = new Scanner(System.in);
        this.welcome = "Hello! I'm GPT0.01!\nWhat can I do for you?";
        this.divider = "\n____________________________________________________________\n";

    }

    public String[] readLine() {

        return this.scanner.nextLine().split(" ");
    }

    public void showLogo() {
        System.out.println(this.logo);
    }
    public void showWelcome() {
        display(this.welcome);
    };

    public void display(String str) {
        System.out.println(this.divider + str + this.divider);
    }

    public void showError(Exception err) {
        display(err.getMessage());
    }
}
