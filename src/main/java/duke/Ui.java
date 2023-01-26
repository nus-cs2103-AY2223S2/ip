package duke;

import java.util.Scanner;

public class Ui {
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm duke.Duke");
        System.out.println("What can I do for you?");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        return str;
    }

    public void showLine() {
        System.out.println("__________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Oops!! There was a problem loading past data. Try rebooting me again!");
    }

    public String showError(String message) {
        return "Oops!! There was an error" + message;
    }
}
