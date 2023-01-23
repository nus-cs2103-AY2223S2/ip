package duke;

import java.util.Scanner;

public class Ui {
    private Scanner reader;

    public Ui() {
        this.reader = new Scanner(System.in);
    }

    public String readCommand() {
        System.out.println("Enter your command!");
        return this.reader.nextLine();
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm duke.Duke\nWhat can I do for you?");
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void wrongDateFormat() {
        System.out.println("Please write your deadline in this format: YYYY-MM-DD HH:mm ");
    }

}
