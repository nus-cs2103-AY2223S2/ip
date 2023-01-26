package duke;

import java.util.Scanner;

public class Ui {
    String lines = "____________________________________________________________";

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public void showError(String msg) {
        System.out.println(msg);
    }

    public void showLine() {
        System.out.println(this.lines);
    }

    public void showEnter() {
        System.out.println();
    }

    public void showLoadingError() {
        System.out.println("Cannot load file. :(\n");
    }

    public void showWelcome() {
        this.showLine();
        System.out.println("Hello! I'm duke.Duke\nWhat can I do for you?");
        this.showLine();
        this.showEnter();
    }

    public void show(String msg) {
        System.out.println(msg);
    }
}
